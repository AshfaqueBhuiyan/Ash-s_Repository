package hw2;

/**
 * Models a simplified baseball-like game called Fuzzball.
 * 
 * @author Ashfaque_Bhuiyan (mbhuiyan)
 */
public class FuzzballGame {
	/**
	 * Number of strikes causing a player to be out.
	 */
	public static final int MAX_STRIKES = 2;

	/**
	 * Number of balls causing a player to walk.
	 */
	public static final int MAX_BALLS = 4;

	/**
	 * Number of outs before the teams switch.
	 */
	public static final int MAX_OUTS = 3;

	// Instance variables
	private int maxInnings;
	private int inning = 1;
	private boolean topOfInning = true;
	private int team0Score = 0;
	private int team1Score = 0;
	private int ballCount = 0;
	private int strikeCount = 0;
	private int outCount = 0;
	private boolean[] bases = new boolean[3];

	/**
	 * Constructor for FuzzballGame. Initializing the game with a set number of
	 * innings.
	 * @param maxInnings Maximum innings for the game.
	 */
	public FuzzballGame(int maxInnings) {
		this.maxInnings = maxInnings;
	}

	// Accessor methods

	/**
	 * Returns the correct inning number.
	 * @return Current inning.
	 */
	public int whichInning() {
		return inning;
	}

	/**
	 * Checks if it is the top of the innings.
	 * @return True if top of the inning, false if bottom.
	 */
	public boolean isTopOfInning() {
		return topOfInning;
	}

	public int getTeam0Score() {
		return team0Score;
	}

	public int getTeam1Score() {
		return team1Score;
	}

	public int getBallCount() {
		return ballCount;
	}

	public int getCalledStrikes() {
		return strikeCount;
	}

	public int getCurrentOuts() {
		return outCount;
	}

	public boolean runnerOnBase(int base) {
		return bases[base - 1];
	}

	// Mutator methods

	/**
	 * Handles the event of a ball being pitched and not struck by the batter.
	 * Increments ball count and checks for a walk situation.
	 */
	public void ball() {
		if (gameEnded())
			return;

		if (++ballCount > MAX_BALLS) {
			ballCount = 0;
			advanceRunnersOnWalk();
		}
	}

	/**
	 * Handles the event of a strike, swung or called.
	 * @param swung True if the batter swung at the pitch, false otherwise.
	 */
	public void strike(boolean swung) {
		if (gameEnded()) {
			return; // Prevent any actions if the game has ended.
		}

		if (swung || ++strikeCount > MAX_OUTS) {
			batterOut();
		}
	}

	// Additional logic for handling hits, caught fly, resetting counts, checking for side switch,
	// advancing runners, etc.
	public void hit(int distance) {
		if (gameEnded()) {
			return;
		}

		resetCounts();
		if (distance < 15) {
			outCount++;
		} else {
			int basesToAdvance = distance < 150 ? 1 : distance < 200 ? 2 : distance < 250 ? 3 : 4;
			shiftRunners(basesToAdvance);
		}
		checkForSideSwitch();
	}

	public void caughtFly() {
		if (gameEnded()) {
			return;
		}

		outCount++;
		resetCounts(); // Ensure counts are reset after a caught fly
		checkForSideSwitch();
	}

	// Helper methods

	/**
	 * Resets ball and strike counts, typically called after a batter's turn ends.
	 */
	private void resetCounts() {
		ballCount = 0;
		strikeCount = 0;
	}

	/**
	 * Switches the side or ends the inning as appropriate when out count reaches maximum.
	 */
	private void checkForSideSwitch() {
		if (outCount >= MAX_OUTS) {
			outCount = 0;
			clearBases();
			if (topOfInning) {
				topOfInning = false;
			} else {
				topOfInning = true;
				inning++;
			}
		}
	}

	/**
	 * Clears the bases, used when switching sides or ending the inning.
	 */
	private void clearBases() {
		for (int i = 0; i < bases.length; i++) {
			bases[i] = false; // Clear each base
		}
	}

	/**
	 * Advances runners on a walk. Runners only advance if forced.
	 */
	private void advanceRunnersOnWalk() {
		if (!bases[0]) {
			bases[0] = true;
			return;
		}

		for (int i = 2; i >= 0; i--) {
			if (i == 2 && bases[i]) {
				incrementScore();
			} else if (bases[i]) {
				bases[i + 1] = true;
			}
			bases[i] = false;
		}

		bases[0] = true; // Batter takes first base
	}

	/**
	 * Handles the batter getting out and checks whether the side should switch.
	 */
	private void batterOut() {
		if (++outCount >= MAX_OUTS) {
			switchSides();
		} else {
			resetCounts();
		}
	}

	/**
	 * Switches sides between innings or when the out count reaches MAX_OUTS.
	 */
	private void switchSides() {
		clearBases();
		outCount = 0;
		resetCounts();
		if (topOfInning) {
			topOfInning = false;
		} else {
			topOfInning = true;
			inning++;
		}
	}

	private void shiftRunners(int basesToAdvance) {
		boolean[] newBases = new boolean[3];
		if (basesToAdvance == 4) { // Home run
			incrementScore(); // Batter scores
			for (boolean base : bases) {
				if (base)
					incrementScore(); // Each runner on base scores
			}
		} else {
			for (int i = 0; i < 3; i++) {
				if (bases[i] && i + basesToAdvance < 3) {
					newBases[i + basesToAdvance] = true;
				} else if (bases[i]) {
					incrementScore();
				}
			}
			if (basesToAdvance <= 3)

				newBases[basesToAdvance - 1] = true;
		}
		bases = newBases;
	}

	private void incrementScore() {
		if (topOfInning) {
			team0Score++;
		} else {
			team1Score++;
		}
	}

	/**
	 * Determines if the game has ended based on the inning and whether it's the top or bottom of the inning.
	 * @return True if the game has ended, false otherwise.
	 */
	public boolean gameEnded() {
		return inning > maxInnings || (inning == maxInnings && !topOfInning && outCount >= MAX_OUTS);
	}

	public String getBases() {
		return (runnerOnBase(1) ? "X" : "o") + (runnerOnBase(2) ? "X" : "o") + (runnerOnBase(3) ? "X" : "o");
	}

	public String toString() {
		String bases = getBases();
		String topOrBottom = (isTopOfInning() ? "T" : "B");
		String fmt = "%s Inning:%d [%s] Score:%d-%d Balls:%d Strikes:%d Outs:%d";
		return String.format(fmt, bases, whichInning(), topOrBottom, getTeam0Score(), getTeam1Score(), getBallCount(),
				getCalledStrikes(), getCurrentOuts());
	}
}