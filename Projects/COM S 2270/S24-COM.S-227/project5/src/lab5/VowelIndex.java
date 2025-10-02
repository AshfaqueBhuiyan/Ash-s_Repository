package lab5;

public class VowelIndex {

	public static int firstVowelIndex(String s) {

		for (int i = 0; i < s.length(); i++) {
			if ("aeiouAEIOU".indexOf(s.charAt(i)) >= 0) {

				// Found a vowel, return its index
				return i;
			}
		}
		// No vowel found
		return -1;
	}

	public static void main(String[] args) {

		System.out.println(firstVowelIndex("hello"));
		System.out.println(firstVowelIndex("sky"));
	}
}
