package hw2;

/**
 * Models a simplified baseball-like game called Fuzzball.
 * 
 * @author Ashfaque_Bhuiyan (mbhuiyan)
 */
public class FuzzballGame {
	public static final int MAX_STRIKES = 2;
	public static final int MAX_BALLS = 4;
	public static final int MAX_OUTS = 3;

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
	 * Constructs a FuzzballGame object.
	 */
	public FuzzballGame(int maxInnings) {
		this.maxInnings = maxInnings;
	}

	// Accessor methods
	public int whichInning() {
		return inning;
	}

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
		return bases[base - 1]; // Adjusting for 0-based indexing
	}

	// Mutator methods
	public void ball() {
		if (gameEnded())
			return; // Prevent any actions if the game has ended

		ballCount++;
		if (ballCount > MAX_BALLS) {
			ballCount = 0; // Reset ball count
			advanceRunnersOnWalk();
		}
	}

	public void strike(boolean swung) {
		if (gameEnded()) {
			return; // Exit method if game has ended
		}

		if (swung) { // Check if the batter swung
			outCount++;
			resetCounts();
			checkForSideSwitch();
		} else {
			if (strikeCount < MAX_STRIKES) {
				strikeCount++;
			}
			if (strikeCount >= MAX_STRIKES) {
				outCount++;
				resetCounts();
				checkForSideSwitch();
			}
		}
	}

	public void hit(int distance) {
		if (gameEnded()) {
			return; // Exit method if game has ended
		}

		resetCounts(); // Reset ball and strike count
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
			return; // Exit method if game has ended
		}

		outCount++;
		checkForSideSwitch();
	}

	// Helper methods
	private void resetCounts() {
		ballCount = 0;
		strikeCount = 0;
	}

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

	private void clearBases() {
		for (int i = 0; i < bases.length; i++) {
			bases[i] = false; // Clear each base
		}
	}

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
			if (basesToAdvance <= 3) // This line ensures that for hits less than a home run, the batter advances to
										// a base.
				newBases[basesToAdvance - 1] = true; // Ensure the batter takes the correct base on hits less than home
														// runs
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

	public boolean gameEnded() {
		// Correctly determines if the game has ended
		return inning > maxInnings || (inning == maxInnings && !topOfInning && outCount >= MAX_OUTS);
	}

	public String getBases() {
		StringBuilder sb = new StringBuilder();
		for (boolean base : bases) {
			sb.append(base ? "X" : "o");
		}
		return sb.toString();
	}

	public String toString() {

		String baseStatus = getBases(); // Implement a method to get bases as a string
		String inningPhase = topOfInning ? "Top" : "Bottom";
		return String.format("Inning: %d (%s) | Score: %d-%d | Balls: %d | Strikes: %d | Outs: %d | Bases: %s", inning,
				inningPhase, team0Score, team1Score, ballCount, strikeCount, outCount, baseStatus);
	}
}