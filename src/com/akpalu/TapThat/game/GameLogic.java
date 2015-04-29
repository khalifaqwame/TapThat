package com.akpalu.TapThat.game;

import android.graphics.Rect;
import android.media.SoundPool;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import com.akpalu.TapThat.game.actor.Actor;

import java.util.ArrayList;
import java.util.List;

/**
 * Controls user input events.
 * Detects tapThat hits, increases the score and resets the tapThat
 * 
 * @author Qwame
 * 
 */
final class GameLogic implements OnTouchListener {
	private final int HIT_MARGIN = 5;
	private final GameController game;
	private final Score score;
	private final List<Actor> actors = new ArrayList<Actor>();
	private final SoundPool soundPool;
	private final int tapThatSoundId;

	public GameLogic(GameController game, Score score, SoundPool soundPool, 
			int tapThatSoundId) {
		this.game = game;
		this.score = score;
		this.soundPool = soundPool;
		this.tapThatSoundId = tapThatSoundId;
	}
	
	/**
	 * Adds an actor to the game logic
	 * @param Actor
	 */
	void addActor(Actor a) {
		actors.add(a);
	}

	/**
	 * When the screen is touched, check to see if a tapThat
	 * has been hit
	 */
	@Override
	public boolean onTouch(View v, MotionEvent ev) {
		final int action = ev.getAction();
		switch (action & MotionEvent.ACTION_MASK) {
		case MotionEvent.ACTION_DOWN:
			// Deliberately no break here
		case MotionEvent.ACTION_POINTER_DOWN:
			// Find the index of the action (for multitouch e.g.
			// 0 is the first finger down, 1 is the second)
			int actionIndex = ev.getActionIndex();
			float x = ev.getX(actionIndex);
			float y = ev.getY(actionIndex);
			// Define a "hit area" that's wider than the point given
			Rect hitArea = new Rect((int) x - HIT_MARGIN, (int) y - HIT_MARGIN,
					(int) x + HIT_MARGIN, (int) y + HIT_MARGIN);
			for (Actor a : actors) {
				if (a.isOverlapping(hitArea)) {
					if (a.isVisible() && !game.isPaused()) {
						score.add(1);
						a.hit();
						soundPool.play(tapThatSoundId, 1, 1, 1, 0, 1f);
					}
				}
			}
			break;
		}
		return true;
	}

}
