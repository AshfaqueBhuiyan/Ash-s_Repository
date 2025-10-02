package lab5;

public class NameInitials {

	public static String getInitials(String fullName) {
		String[] parts = fullName.split(" ");
		StringBuilder initials = new StringBuilder();
		
		for (String part : parts) {
			if (!part.isEmpty()) {
				initials.append(part.charAt(0));
			}
		}
		return initials.toString();
	}

	public static void main(String[] args) {

		System.out.println(getInitials("Cher"));
		System.out.println(getInitials("Edna del Humboldt von der Schooch"));
	}
}
