package lab5;



/**
 * An example with some buggy loops fixed.
 */
public class SimpleLoops {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int count = countP("Mississippi");
		System.out.println(count);

		int lastP = findLastP("Mississippi");
		System.out.println(lastP);

		int firstP1 = findFirstP("stop");
		System.out.println(firstP1);

		int firstP2 = findFirstP("xxxyyyzzz");
		System.out.println(firstP2);
	}

	/**
	 * Returns the number of P's in a string.
	 * 
	 * @param s the string to examine
	 * @return number of P's in s
	 */
	private static int countP(String s) {
		int count = 0;
		for (int i = 0; i < s.length(); i++) {
			if (isLetterP(s.charAt(i))) {
				count++;
			}
		}
		return count;
	}

	/**
	 * Returns the index of the last P in a string, or -1 if the 
	 * string contains no P's.
	 * @param s 
	 * 	 the string to examine
	 * @return 
	 * 	 index of the last P, or -1
	 */
	private static int findLastP(String s) {
		for (int i = s.length() - 1; i >= 0; i--) {
			if (isLetterP(s.charAt(i))) {
				return i;
			}
		}
		
		// didn't find a P
		return -1;
	}

	/**
	 * Returns the index of the first P in a string, or -1 if the 
	 * string contains no P's.
	 * @param s 
	 * 	 the string to examine
	 * @return 
	 * 	 index of the first P, or -1
	 */
	private static int findFirstP(String s) {
		for (int i = 0; i < s.length(); i++) {
			if (isLetterP(s.charAt(i))) {
				return i;
			}
		}
		
		// didn't find a P
		return -1;
	}

	/**
	 * Returns true if the given character is the letter "P" (lowercase 
	 * or uppercase), false otherwise.
	 * 	 the character to check
	 * @return 
	 * 	 true if ch is 'P' or 'p', false otherwise
	 */
	private static boolean isLetterP(char ch) {
		return ch == 'P' || ch == 'p';
	}
}
