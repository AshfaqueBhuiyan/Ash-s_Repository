package lab7;

import java.io.File;

public class FileCounter {

	public static void main(String[] args) {
		// Adjust accordingly to get the directory you want.
		File rootDirectory = new File("../COMS227_HW3_S24_skeleton");

		int count = countFiles(rootDirectory);
		System.out.println("Total number of files: " + count);
	}

	/**
	 * Counts all files within a directory and its subdirectories.
	 */
	public static int countFiles(File f) {
		if (!f.isDirectory()) {
			// Base case: f is a file, count 1
			return 1;
		} else {
			// Recursive case: f is a directory, so go through the files and directories
			// it contains, and recursively call this method on each one
			int count = 0;
			File[] files = f.listFiles();
			if (files != null) { // null if the directory is empty or an I/O error occurs
				for (File file : files) {
					count += countFiles(file);
				}
			}
			return count;
		}
	}
}
