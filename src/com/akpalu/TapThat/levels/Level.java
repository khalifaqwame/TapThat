package com.akpalu.TapThat.levels;

import java.io.Serializable;

/**
 * A tapThat Challenge level
 * @author Qwame
 *
 */
public class Level implements Serializable {
	private static final long serialVersionUID = 32551L;
	private int tapThats;
	private int targetScore;
	private int timeLimit;
	private String title;
	private String description;
	private int number;
	
	public Level (int number, int popUpTapThat, int targetScore, int timeLimit, String title, String description) {
		this.setTapThats(popUpTapThat);
		this.setTargetScore(targetScore);
		this.setTimeLimit(timeLimit);
		this.setTitle(title);
		this.setDescription(description);
		this.setNumber(number);
	}

	/**
	 * Gets the number of tapballs in the level
	 * @return
	 */
	public int getTapThats() {
		return tapThats;
	}

	/**
	 * Sets the number of tapballs in the level
	 * @param tapThats
	 */
	private void setTapThats(int tapThats) {
		this.tapThats = tapThats;
	}

	/**
	 * Gets the target score
	 * @return
	 */
	public int getTargetScore() {
		return targetScore;
	}

	
	/**
	 * Sets the target score
	 * @param targetScore
	 */
	private void setTargetScore(int targetScore) {
		this.targetScore = targetScore;
	}

	/**
	 * Gets the time limit
	 * @return
	 */
	public int getTimeLimit() {
		return timeLimit;
	}

	/**
	 * Sets the time limit
	 * @param timeLimit
	 */
	private void setTimeLimit(int timeLimit) {
		this.timeLimit = timeLimit;
	}

	/**
	 * Gets the title
	 * @return
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the title
	 * @param title
	 */
	private void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Gets the description
	 * @return
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description
	 * @param description
	 */
	private void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets the level number
	 * @return
	 */
	public int getNumber() {
		return number;
	}

	/**
	 * Sets the level number
	 * @param number
	 */
	private void setNumber(int number) {
		this.number = number;
	}
}
