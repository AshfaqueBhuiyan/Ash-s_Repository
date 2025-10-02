package hw3;

import java.util.ArrayList;
import api.Cell;
import api.Direction;
import api.Exit;
//import api.GameFileUtil;
//import api.Lizard;
import api.ScoreUpdateListener;
import api.ShowDialogListener;
import api.Wall;
import api.BodySegment;

/**
 * Represents the game logic for managing lizards on a 2D grid. This class
 * handles the movement of lizards, interactions with walls, exits, and tracking
 * the game state.
 * 
 * @see Lizard
 * @see api.Cell
 * @see api.Wall
 * @see api.Exit
 * @see GameFileUtil#load(String, LizardGame)
 * 
 * @author Ashfaque_Bhuiyan
 */
public class LizardGame {
	// Class fields, constructors, and methods follow.
	private Cell[][] grid;
	private ArrayList<Lizard> lizards;
	private ShowDialogListener dialogListener;
	private ScoreUpdateListener scoreListener;

	public LizardGame(int width, int height) {
		resetGrid(width, height); // This will also initialize the lizards array
	}

	public void load(String filePath) {
		GameFileUtil.load(filePath, this); // Ensure this correctly initializes the game state
	}

	/**
	 * Resets the game grid to a specified width and height. This method clears any existing game state and initializes a new grid.
	 *
	 * @param width  the width of the grid to be initialized
	 * @param height the height of the grid to be initialized
	 * 
	 * @see #getCell(int, int)
	 */
	public void resetGrid(int width, int height) {
		grid = new Cell[width][height];
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				grid[i][j] = new Cell(i, j);
			}
		}
		lizards = new ArrayList<>(); // Clearing or re-initializing the list of lizards
	}

	public int getWidth() {
		return grid.length;
	}

	public int getHeight() {
		return grid[0].length;
	}

	/**
	 * Adds a wall to the game grid at the specified cell. If the cell already contains another game element, this method will handle it according to the game's rules.
	 *
	 * @param wall the Wall object to be added to the game grid
	 * 
	 * @see api.Wall
	 * @see #getCell(int, int)
	 */
	public void addWall(Wall wall) {
		Cell cell = wall.getCell();
		cell.placeWall(wall);
	}

	/**
	 * Adds an exit to the game grid. Exits are used to remove lizards from the game when reached.
	 *
	 * @param exit the Exit object to be added to the game grid
	 * 
	 * @see api.Exit
	 * @see #getCell(int, int)
	 */
	public void addExit(Exit exit) {
		Cell cell = exit.getCell();
		cell.placeExit(exit);
	}

	public ArrayList<Lizard> getLizards() {
		return lizards;
	}

	/**
	 * Adds a lizard to the game grid. This method places the lizard's body segments on the grid according to their positions in the lizard object.
	 *
	 * @param lizard the Lizard object to be added to the game
	 * 
	 * @see Lizard
	 * @see api.BodySegment
	 */
	public void addLizard(Lizard lizard) {
		lizards.add(lizard);
		for (BodySegment segment : lizard.getSegments()) {
			Cell cell = segment.getCell();
			if (cell != null) {
				cell.placeLizard(lizard);
			}
		}
		updateScore();
	}

	/**
	 * Removes a lizard from the game grid. This method is typically called when a lizard reaches an exit or when the game state needs to be reset or updated.
	 *
	 * @param lizard the Lizard object to be removed from the game
	 * 
	 * @see Lizard
	 */

	public void removeLizard(Lizard lizard) {
		if (lizards.remove(lizard)) {
			for (BodySegment segment : lizard.getSegments()) {
				Cell cell = segment.getCell();
				if (cell != null) {
					cell.removeLizard();
				}
			}
			updateScore();
			checkWinCondition();
		}
	}

	/**
	 * Retrieves the Cell object located at the specified column and row of the game grid.
	 *
	 * @param col the column of the cell to retrieve
	 * @param row the row of the cell to retrieve
	 * 
	 * @return the Cell located at the specified column and row, or null if the coordinates are out of bounds
	 * 
	 * @see api.Cell
	 */
	public Cell getCell(int col, int row) {
		if (col < 0 || col >= getWidth() || row < 0 || row >= getHeight()) {
			return null;
		}
		return grid[col][row];
	}

	/**
	 * Gets the cell adjacent to the specified cell in the given direction. This method is used to calculate the next position of a lizard based on its movement direction.
	 *
	 * @param col       the column of the starting cell
	 * @param row       the row of the starting cell
	 * @param direction the direction in which to find the adjacent cell
	 * 
	 * @return the Cell adjacent to the given cell in the specified direction, or null if the adjacent cell is out of bounds
	 * 
	 * @see api.Cell
	 * @see api.Direction
	 */
	public Cell getAdjacentCell(int col, int row, Direction direction) {
		switch (direction) {
		case UP:
			return row > 0 ? getCell(col, row - 1) : null;
		case DOWN:
			return row < getHeight() - 1 ? getCell(col, row + 1) : null;
		case LEFT:
			return col > 0 ? getCell(col - 1, row) : null;
		case RIGHT:
			return col < getWidth() - 1 ? getCell(col + 1, row) : null;
		default:
			return null;
		}
	}

	/**
	 * Determines whether a cell at the specified column and row is available for a lizard to move into. 
	 * A cell is considered available if it does not contain a wall, exit, or another lizard.
	 *
	 * @param col the column of the cell to check
	 * @param row the row of the cell to check
	 * 
	 * @return true if the cell is available, false otherwise
	 * 
	 * @see #getCell(int, int)
	 */
	public boolean isAvailable(int col, int row) {
		Cell cell = getCell(col, row);
		return cell != null && cell.getWall() == null && cell.getLizard() == null;
	}

	/**
     * Moves a lizard in the specified direction. This method checks for
     * collisions with walls, other lizards, and exits. If a lizard reaches an
     * exit, it is removed from the game.
     * 
     * @param col Column of the cell where the movement initiates.
     * @param row Row of the cell where the movement initiates.
     * @param dir Direction of the movement.
     */
	public void move(int col, int row, Direction dir) {
		Cell startingCell = getCell(col, row);
		if (startingCell == null || startingCell.getLizard() == null) {
			return; // Early exit if starting cell is invalid or has no lizard
		}

		Lizard lizard = startingCell.getLizard();
		ArrayList<BodySegment> segments = lizard.getSegments();

		// Determine the next head cell based on direction
		Cell nextHeadCell = getAdjacentCell(col, row, dir);
		if (nextHeadCell == null || nextHeadCell.getWall() != null || nextHeadCell.getLizard() != null) {
			return; // Early exit if the next cell is invalid, has a wall, or another lizard
		}

		// Temporarily clear the lizard from current cells to avoid self-collision
		for (BodySegment segment : segments) {
			segment.getCell().removeLizard();
		}

		// Store the current cell of the first segment to update the cell of the last segment later
		Cell temp = segments.get(0).getCell();

		// Move the head to the new cell
		segments.get(0).setCell(nextHeadCell);

		// Shift all other segments to follow the head
		for (int i = 1; i < segments.size(); i++) {
			Cell currentCell = segments.get(i).getCell(); // Store current cell
			segments.get(i).setCell(temp); // Move segment to the cell of the segment in front of it
			temp = currentCell; // Update temp to the current cell for the next segment in the loop
		}

		// Place the lizard back in the cells
		for (BodySegment segment : segments) {
			segment.getCell().placeLizard(lizard);
		}

		// Handle special cell types like exits
		if (nextHeadCell.getExit() != null) {
			removeLizard(lizard); // Remove lizard if it reached an exit
		} else {
			updateScore(); // Update score for a successful move
		}
	}

	/**
	 * Updates the game's score and checks for win conditions. This method is called after significant game events, such as adding or removing a lizard.
	 */ 
	private void updateScore() {
		if (scoreListener != null) {
			scoreListener.updateScore(lizards.size());
		}
	}

	/**
	 * Checks if the win condition has been met, typically when all lizards have reached exits. If the win condition is met, 
	 * it triggers the appropriate game-ending behavior.
	 */
	private void checkWinCondition() {
		if (lizards.isEmpty() && dialogListener != null) {
			dialogListener.showDialog("Congratulations! You've won the game.");
		}
	}

/**
 * 
 * Sets the listeners for dialog and score update events within the game. These listeners
 * are used to handle user interface interactions that occur in response to game events, such
 * as displaying messages or updating the score display.
 * 
 * @param dialogListener
 * @param scoreListener
 */
	public void setListeners(ShowDialogListener dialogListener, ScoreUpdateListener scoreListener) {
		this.dialogListener = dialogListener;
		this.scoreListener = scoreListener;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("LizardGame State:\n");
		for (int row = 0; row < getHeight(); row++) {
			for (int col = 0; col < getWidth(); col++) {
				Cell cell = getCell(col, row);
				sb.append(cell.getLizard() != null ? "L"
						: cell.getWall() != null ? "W" : cell.getExit() != null ? "E" : ".");
				sb.append(" ");
			}
			sb.append("\n");
		}
		sb.append("Number of Lizards: ").append(lizards.size()).append("\n");
		return sb.toString();
	}
}
