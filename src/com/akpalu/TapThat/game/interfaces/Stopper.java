package com.akpalu.TapThat.game.interfaces;

/**
 * Objects of this type can stop a game.
 * @author Qwame
 *
 */
public interface Stopper {
	/**
	 * Returns true when the game should stop.
	 * @return
	 */
	public boolean needToStop();
}
