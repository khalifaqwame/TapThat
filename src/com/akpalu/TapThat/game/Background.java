package com.akpalu.TapThat.game;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import com.akpalu.TapThat.game.interfaces.Drawable;

/**
 * A game's background
 * @author Qwame
 *
 */
class Background implements Drawable {
	private Bitmap bm;

	/**
	 * Creates a background
	 * @param width in pixels
	 * @param height in pixels
	 * @param bm the background image
	 */
	Background(int width, int height, Bitmap bm) {
		// Scale the background to the game board size
		BitmapFactory.Options options = new BitmapFactory.Options(); 
		options.inPurgeable = true;
		this.bm = Bitmap.createScaledBitmap(bm, width, height, false);
	}

	/**
	 * Draws this background on a canvas
	 * @param canvas from the Android framework
	 */
	@Override
	public void draw(Canvas canvas) {
		canvas.drawBitmap(bm, 0, 0, null);
	}
}