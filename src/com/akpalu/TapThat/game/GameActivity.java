package com.akpalu.TapThat.game;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import com.akpalu.TapThat.R;
import com.akpalu.TapThat.activities.Pause;
import com.akpalu.TapThat.activities.VolumeControlActivity;
import com.akpalu.TapThat.game.loops.GraphicsLoop;
import com.akpalu.TapThat.levels.EndLevel;
import com.akpalu.TapThat.levels.Level;
import com.akpalu.TapThat.levels.LevelSelect;
import com.akpalu.TapThat.levels.StartLevel;

/**
 * The game activity.
 * 
 * @author Qwame
 * 
 */
public class GameActivity extends VolumeControlActivity {
	private GameController game;
	private Level level;
	private boolean firstRun = true;

	/**
	 * Calls the StartLevel activity to show the level start screen
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		level = (Level) getIntent().getExtras().getSerializable("level");
		GameActivity gameActivity = this;
		ImageView pauseButton = (ImageView) findViewById(R.id.game_pause_image);
		pauseButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				pause();
			}
		});	
		Intent intent = new Intent(gameActivity, StartLevel.class);
		intent.putExtra("level", level);
		startActivity(intent);
	}

	/**
	 * Unpause the game when the activity is resumed
	 */
	@Override
	protected void onResume() {
		// game won't be initialized for the first onResume call
		// as onResume is called when an activity first starts
		if (game == null && !firstRun) {
			createGame();
		}
		
		if (game != null && game.isStarted()) {
			game.unPause();
		}
		super.onResume();
	}

	/**
	 * Creates the game
	 */
	private void createGame() {
		// Set the width and height
		ImageView placeholderBackground = (ImageView) findViewById(R.id.game_background_placeholder);
		int width = placeholderBackground.getWidth();
		int height = placeholderBackground.getHeight();

		GameBuilderDirector gameBuilderDirector = new GameBuilderDirector(this, this, 
				this.getResources(),width, height, level);
		game = gameBuilderDirector.getGame();
		// Hide the placeholder gameboard and show the proper gameboard
		placeholderBackground.setVisibility(View.GONE);
		GraphicsLoop graphicsLoop = (GraphicsLoop) findViewById(R.id.canvas);
		graphicsLoop.setVisibility(View.VISIBLE);
		game.start();
		game.pause();
	}

	/**
	 * If the activity is stopped and restarted, go to the level select screen.
	 */
	@Override
	protected void onRestart() {
		super.onRestart();
		Intent intent = new Intent(this, LevelSelect.class);
		startActivity(intent);
	}

	/**
	 * Disable the back button
	 */
	@Override
	public void onBackPressed() {

		if (!game.isPaused()) {
			pause();
		} else {
			game.unPause();
				super.onBackPressed();
		}


	}

	/**
	 * Pause the game with the menu button
	 */
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_MENU) {
			if (!game.isPaused()) {
				pause();
			} else {
				game.unPause();
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	/**
	 * If this activity is paused, pause the game
	 */
	@Override
	public void onPause() {
		firstRun = false;
		if (game != null) {
			if (!game.isPaused()) {
				game.pause();
			}
		}
		super.onPause();
	}

	/**
	 * Explicitly end the activity when it's not visible. This significantly
	 * reduces the frequency of out of memory errors in the rest of the game.
	 */
	@Override
	public void onStop() {
		finish();
		super.onStop();
	}

	/**
	 * When a level ends, show the "End Level" activity
	 */
	public void endLevel() {
		int score = game.getScore();
		Level level = game.getLevel();
		Intent intent = new Intent(this, EndLevel.class);
		intent.putExtra("score", score);
		intent.putExtra("level", level);
		startActivity(intent);
	}
	
	/**
	 * Pauses the game and shows the game paused screen
	 */
	private void pause() {
		game.pause();
		Intent intent = new Intent(this, Pause.class);
		startActivity(intent);
	}
}