package hw2;

public class Tennis {
    /* Everything below this line is given to students, either because we
     haven't covered loops yet, or because we want to ensure very precise
     formatting so that we can test by comparing strings which are printed by
     the same function.  */

    /**
     * Prints out what the scoreboard must indicate.  It counts sets and games
     * in a natural way, with whole numbers.  However points within a game are
     * counted using the nonconsecutive numbers 0, 15, 30, 40.  When there is
     * a deuce, it is indicated as 40 for both players.  If one player has an
     * advantage, their score is represented as "AD", while the other player's
     * score is simultaneously indicated as "--".  When counting score in
     * tiebreak games, we simply use normal counting.
     */
    @Override
    public String toString() {
	String playerAServingIndicator;
	String playerBServingIndicator;
	if (getPlayerAServing()) {
	    playerAServingIndicator = "S>";
	    playerBServingIndicator = "  ";
	} else {
	    playerAServingIndicator = "  ";
	    playerBServingIndicator = "S>";
	}
	String returned =
	    String.format(
                "%2s %-12s %2d %2d %6s\n%2s %-12s %2d %2d %6s\n",
		playerAServingIndicator,
		getPlayerAName(),
		getPlayerASets(),
		getPlayerAGames(),
		getPlayerAScore(),
		playerBServingIndicator,
		getPlayerBName(),
		getPlayerBSets(),
		getPlayerBGames(),
		getPlayerBScore());
	// System.out.println(returned);
	return returned;
    }

    /**
     * For testing purposes, converts a string of a's and b's into a sequence
     * of calls to winPoint, using an argument of true if the corresponding
     * character is an a, and false if the corresponding character is a b.
     * Provides a convenient way to run many winPoint method calls with
     * very concise notation.
     *
     * @param pointList - "script" that is converted into winPoint method
     * calls.
     */
    public void runPoints(String pointList) {
	for (int i = 0; i < pointList.length(); ++i) {
	    if (pointList.charAt(i) == 'a') {
		winPoint(true);
	    } else if (pointList.charAt(i) == 'b') {
		winPoint(false);
	    } else {
		// skip the character silently
	    }
	}
    }

    /**
     * For testing purposes, converts a string of a's and b's into a sequence
     * of calls to winGame, using an argument of true if the corresponding
     * character is an a, and false if the corresponding character is a b.
     * Provides a convenient way to run many winGame method calls with
     * very concise notation.
     *
     * @param gameList - "script" that is converted into winGame method
     * calls.
     */
    public void runGames(String gameList) {
	for (int i = 0; i < gameList.length(); ++i) {
	    if (gameList.charAt(i) == 'a') {
		winGame(true);
	    } else if (gameList.charAt(i) == 'b') {
		winGame(false);
	    } else {
		// skip the character silently
	    }
	}
    }

    /**
     * For testing purposes, converts a string of a's and b's into a sequence
     * of calls to winSet, using an argument of true if the corresponding
     * character is an a, and false if the corresponding character is a b.
     * Provides a convenient way to run many winSet method calls with
     * very concise notation.
     *
     * @param setList - "script" that is converted into winSet method calls.
     */
    public void runSets(String setList) {
	for (int i = 0; i < setList.length(); ++i) {
	    if (setList.charAt(i) == 'a') {
		winSet(true);
	    } else if (setList.charAt(i) == 'b') {
		winSet(false);
	    } else {
		// skip the character silently
	    }
	}
    }
}
