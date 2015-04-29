package com.akpalu.TapThat.levels;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Manages the tapThatChallenge levels
 * @author Qwame
 *
 */
class Levels {	
	static Map<Integer, Level> levels = new LinkedHashMap<Integer, Level>();
	
	static {
		// First Tier: Beginner
		levels.put(1, new Level(1, 5, 23, 15, "Welcome!", "Let's get started.")); // 1/sec
		levels.put(2, new Level(2, 7, 24, 10, "Faster", "Ready for more?"));  // 1.2/sec
		levels.put(3, new Level(3, 8, 42, 15, "Even Faster", "This will test you.")); // 1.5/sec
		levels.put(4, new Level(4, 3, 16, 20, "Time to Relax", "Let's dial it down.")); // 0.4/sec
		levels.put(5, new Level(5, 7, 40, 15, "The Challenge", "Now they're angry.")); // 1.7/sec
		
		// Second Tier: Intermediate
		levels.put(6, new Level(6, 9, 93, 30, "The NEXT level", "MARATHON KENYA STYLE!!!.")); // 2/sec
		levels.put(7, new Level(7, 8, 40, 15, "Up to Speed", "Ready for more?")); // 2.2/sec // Gets difficult here with 1 finger
		levels.put(8, new Level(8, 9, 74, 30, "Endurance", "We'll step it up a notch.")); // 2/sec
		levels.put(9, new Level(9, 4, 40, 30, "Eagle Eye", "Precision counts.")); // 0.9/sec
		levels.put(10, new Level(10, 9, 70, 20, "Fast Fingers", "Let's see what you can do.")); // 2.5/sec
		
		// Third tier: Advanced
		levels.put(11, new Level(11, 10, 40, 10, "Something New", "They're frantic.")); // 2.6/sec
		levels.put(12, new Level(12, 13, 36, 10, "So Many!", "It's tap ball frenzy.")); // 2/sec
		levels.put(13, new Level(13, 10, 60, 20, "Step It Up!", "So quick.")); // 2.7/sec
		levels.put(14, new Level(14, 12, 25, 5, "The Sprint", "With a slow start.")); // 2.8/sec
		levels.put(15, new Level(15, 10, 93, 30, "Hold Steady", "How long can you hold out?")); // 2.6/sec
		
		// Forth tier: Pro
		levels.put(16, new Level(16, 12, 45, 10, "Pro", "Welcome to the big leagues.")); // 3/sec
		levels.put(17, new Level(17, 12, 145, 45, "Like a Boss", "Prepare yourself.")); // 3.5/sec
		levels.put(18, new Level(18, 17, 35, 7, "Done Already?", "This'll test you.")); // 2.8/sec
		levels.put(19, new Level(19, 4, 30, 20, "Change of Pace", "Fast fingers only.")); // 0.9/sec
		levels.put(20, new Level(20, 12, 240, 60, "Ender", "They won't know what hit 'em.")); // 3.5/sec
		
		// Debugging
		levels.put(99, new Level(99, 1, 210, 60, "DEBUG", "Test mode"));
	}
	
	/**
	 * Return the level with the passed number
	 * @param number
	 * @return
	 */
	public static Level get(int number) {
		return levels.get(number);
	}
}