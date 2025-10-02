package hw3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import api.BodySegment;
import api.Cell;
import api.Exit;
//import api.Lizard;
import api.Wall;

/**
 * Utility class for loading game states from a file. This class reads the
 * game grid and lizard positions from a text file and initializes the game
 * accordingly.
 * 
 * @see LizardGame
 * @see LizardGame#addLizard(Lizard)
 * @see LizardGame#addWall(api.Wall)
 * @see LizardGame#addExit(api.Exit)
 * @see api.Cell
 * @see api.Wall
 * @see api.Exit
 * 
 * @author Ashfaque_Bhuiyan
 */
public class GameFileUtil {
	/**
     * Loads the game state from the specified file path into the given LizardGame
     * instance. This includes the grid dimensions, walls, exits, and lizard
     * positions.
     * 
     * @param filePath The path to the game file.
     * @param game The LizardGame instance to be initialized.
     */
    public static void load(String filePath, LizardGame game) {
    	// File reading and processing logic.
        try (Scanner scanner = new Scanner(new File(filePath))) {
            // Reset the game based on the grid dimensions specified in the file
            if (scanner.hasNextLine()) {
                String[] dimensions = scanner.nextLine().trim().split("x");
                int width = Integer.parseInt(dimensions[0]);
                int height = Integer.parseInt(dimensions[1]);
                game.resetGrid(width, height);
            }

            // Process the grid layout for walls and exits
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.isEmpty() || line.startsWith("L")) {
                    break; // Stop if we reach the lizard section or an empty line
                }
                processGridElement(line, game);
            }

            // Process lizards if any, after processing walls and exits
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (!line.isEmpty()) {
                    processLizard(line, game);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filePath);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.err.println("Error processing game file: " + e.getMessage());
        }
    }

    /**
     * Processes a single line from the game configuration file representing a grid element (wall or exit)
     * and updates the game state accordingly. This method is called by #load(String, LizardGame.
     *
     * @param line the line from the configuration file to process
     * @param game the {@code LizardGame} instance being initialized
     * 
     * @throws IllegalArgumentException if the line format does not match expected patterns for walls or exits
     */
    private static void processGridElement(String line, LizardGame game) {
        String[] parts = line.split("\\s+");
        if (parts.length < 3) return; // Basic validation

        int col = Integer.parseInt(parts[1]);
        int row = Integer.parseInt(parts[2]);
        Cell cell = game.getCell(col, row);

        switch (parts[0]) {
            case "W":
                Wall wall = new Wall(cell);
                game.addWall(wall);
                break;
            case "E":
                Exit exit = new Exit(cell);
                game.addExit(exit);
                break;
            default:
                System.err.println("Unknown grid element: " + parts[0]);
        }
    }

    /**
     * Processes a line from the game configuration file that specifies a lizard's segments and positions.
     * Initializes a {@code Lizard} object and its segments based on this line, adding it to the game.
     * This method is intended to be called from #load(String, LizardGame.
     *
     * @param line the line from the configuration file describing a lizard and its segments
     * @param game the {@code LizardGame} instance being initialized
     * 
     * @throws IllegalArgumentException if the line format does not adhere to expected specifications
     */
    private static void processLizard(String line, LizardGame game) {
        String[] parts = line.split("\\s+");
        if (parts.length < 3 || !parts[0].equals("L")) return; // Basic validation

        Lizard lizard = new Lizard();
        ArrayList<BodySegment> segments = new ArrayList<>();

        for (int i = 1; i < parts.length; i += 2) {
            int col = Integer.parseInt(parts[i]);
            int row = Integer.parseInt(parts[i + 1]);
            Cell cell = game.getCell(col, row);
            segments.add(new BodySegment(lizard, cell));
        }

        lizard.setSegments(segments);
        game.addLizard(lizard);
    }
}
