package com.akpalu.TapThat.game;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.media.SoundPool;
import android.widget.TextView;
import com.akpalu.TapThat.R;
import com.akpalu.TapThat.game.actor.Actor;
import com.akpalu.TapThat.game.actor.GameBoard;
import com.akpalu.TapThat.game.interfaces.GameComponent;
import com.akpalu.TapThat.game.interfaces.OnStopListener;
import com.akpalu.TapThat.game.loops.GameLoop;
import com.akpalu.TapThat.game.loops.GraphicsLoop;
import com.akpalu.TapThat.levels.Level;

/**
 * Builds the game
 * @author Qwame
 *
 */
public class GameBuilder {
	private GameLoop gameLoop;
	private GraphicsLoop graphicsLoop;
	private Level level;
	private GameController game;
	private Score score;
	private int tapThatHitSoundId;
	private SoundPool soundPool;
	private GameLogic gameLogic;
	private GameBoard gameBoard; 

	/**
	 * Sets the level around which this game is built
	 * 
	 * @param level
	 */
	public void setLevel(Level level) {
		this.level = level;
	}

	/**
	 * Sets the game board size.
	 * @param width
	 * @param height
	 * @param graphicsLoop2 
	 * @param backgroundPic 
	 */
	public void addGameBoard(int width, int height, Bitmap backgroundPic) {
		gameBoard = new GameBoard(width, height);
		Background background = new Background(width, height, backgroundPic);
		graphicsLoop.register(background);
	}

	/**
	 * Instantiates the loops needed for a game
	 * 
	 * @param graphicsLoop
	 *            A graphicsLoop to be added to the gameLoop
	 */
	public void makeLoops(GraphicsLoop graphicsLoop) {
		gameLoop = new GameLoop();
		this.graphicsLoop = graphicsLoop;
		gameLoop.addGameComponent(graphicsLoop);
	}

	/**
	 * Creates a score entity to keep score
	 */
	public void addScore(final TextView scoreText) {
		score = new Score(level);
		gameLoop.addGameComponent(new GameComponent() {	
			@Override
			public void play(long runTime) {
				scoreText.setText(score.toString());
			}
		});
	}
	
	/**
	 * Creates a count down timer
	 * @param timerText The textview to update with the time
	 */
	public void makeTimer(final TextView timerText) {
		// Set a timer to stop the game after a specified time
		final Timer timer = new Timer(level.getTimeLimit() * 1000);
		gameLoop.addGameComponent(timer);
		gameLoop.registerStoppable(timer);
		gameLoop.addGameComponent(new GameComponent() {	
			@Override
			public void play(long runTime) {
				timerText.setText(timer.toString());
			}
		});
	}

	/**
	 * Shows the level end screen when the level finishes
	 * @param endLevelStarter
	 */
	public void addLevelEnd(final GameActivity gameActivity) {
		// Show the level end screen when the game stops
		gameLoop.addStopListener(new OnStopListener() {
			@Override
			public void onStop() {
				gameActivity.endLevel();
			}
		});
	}

	/**
	 * Adds a sound pool so the game can play sound
	 * @param context
	 */
	public void addSoundPool(Context context) {
		soundPool = new SoundPool(4, AudioManager.STREAM_MUSIC, 0);
		tapThatHitSoundId = soundPool.load(context, R.raw.hit, 1);
	}

	/**
	 * Adds tapThats to the game
	 * @param tapThatPic
	 */
	public void addtapThats(Bitmap tapThatPic) {
		for (int i = 0; i < level.getTapThats(); i++) {
			Actor tapThat = addTapBalls(tapThatPic, gameBoard);
			graphicsLoop.register(tapThat);
		}
	}

	/**
	 * Adds an individual tapballs to the game
	 * @param tapThatPic
	 * @param gameBoard
	 * @return
	 */
	private Actor addTapBalls(final Bitmap tapThatPic, final GameBoard gameBoard) {
		final Actor tapBalls = new Actor(gameBoard, tapThatPic, gettapThatSize());
		// Set the size of the tapBalls to be a fixed % of the gameboard's height
		gameLoop.addGameComponent(tapBalls);
		gameLogic.addActor(tapBalls);

		return tapBalls;
	}
	
	private int gettapThatSize() {
		return (int) (gameBoard.getWidth() * 0.13);
	}
	
	public void addLogic(Bitmap tapThatPic) {
		gameLogic = new GameLogic(game, score, soundPool, tapThatHitSoundId);
		graphicsLoop.setOnTouchListener(gameLogic);
	}

	/**
	 * Completes the game building process
	 */
	public GameController getGame() {
		return game;
	}

	public void addGame() {
		this.game = new GameController(score, level, gameLoop);
	}
}
