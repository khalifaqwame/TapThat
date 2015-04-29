package com.akpalu.TapThat.game;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.TextView;
import com.akpalu.TapThat.R;
import com.akpalu.TapThat.game.loops.GraphicsLoop;
import com.akpalu.TapThat.levels.Level;

/**
 * Directs the game builder process
 * @author Qwame
 *
 */
public class GameBuilderDirector {
	private final GameBuilder gameBuilder;

	/**
	 * Builds the game
	 * @param gameActivity Provides access to the Android views
	 * @param context Provides a sound pool
	 * @param resources Provides access to images
	 * @param width The game's width
	 * @param height The game's height
	 * @param level The level to play
	 */
	public GameBuilderDirector(GameActivity gameActivity, Context context,
			Resources resources, int width, int height,
			Level level) {
		this.gameBuilder = new GameBuilder();
		GraphicsLoop graphicsLoop = (GraphicsLoop) gameActivity
				.findViewById(R.id.canvas);
		gameBuilder.makeLoops(graphicsLoop);
		gameBuilder.setLevel(level);
		Bitmap backgroundPic = BitmapFactory.decodeResource(resources,
				R.drawable.background);
		gameBuilder.addGameBoard(width, height, backgroundPic);
		
		TextView scoreText = (TextView) gameActivity
				.findViewById(R.id.game_score);
		gameBuilder.addScore(scoreText);
		gameBuilder.addGame();

		gameBuilder.addLevelEnd(gameActivity);

		gameBuilder.addSoundPool(context);

		// Set up the game's timer
		TextView timerText = (TextView) gameActivity.findViewById(R.id.game_time);
		gameBuilder.makeTimer(timerText);

		Bitmap tapThatPic = (BitmapFactory.decodeResource(resources,
				R.drawable.screenballs));
		gameBuilder.addLogic(tapThatPic);
		gameBuilder.addtapThats(tapThatPic);
	}
	
	/**
	 * Returns the game
	 * @return
	 */
	GameController getGame() {
		return gameBuilder.getGame();
	}
}
