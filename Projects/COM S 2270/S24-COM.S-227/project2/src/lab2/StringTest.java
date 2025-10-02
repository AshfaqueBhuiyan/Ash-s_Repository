package lab2;

public class StringTest {
	public static void main(String[] args) {
		/**
		 *  Prints a simple Message.
		 */
        String message = "Hello World! My name is Ashfaque.";

        /**
         * Printing Characters at different indexes.
         */
        System.out.println("Characters at different indexes:");
        char theChar = message.charAt(0);
        System.out.println(theChar);

        theChar = message.charAt(1);
        System.out.println(theChar);
        
        /**
         * Printing in UpperCase.
         */
        System.out.println("UpperCase version of message: " + message.toUpperCase());

        /**
         *  Printing the first 5 Characters using Substring Method.
         */
        String firstFiveChars = message.substring(0, 5);
        System.out.println("First 5 characters: " + firstFiveChars);
    }
}
