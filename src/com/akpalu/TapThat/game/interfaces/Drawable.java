package com.akpalu.TapThat.game.interfaces;

import android.graphics.Canvas;

/**
 * Implemented by classes that can be drawn
 * @author Qwame
 *
 */
public interface Drawable {
	/**
	 * Draws to a canvas
	 * @param canvas
	 */
	public void draw(Canvas canvas);
}
