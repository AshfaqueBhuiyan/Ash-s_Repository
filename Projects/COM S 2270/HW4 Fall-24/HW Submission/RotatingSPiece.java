package hw4;

import api.Cell;
import api.Icon;
import api.Position;

/**
 * Represents a Rotating S-shaped piece in the game. This piece consists of four
 * cells arranged in a configuration resembling the letter "S". The specific
 * positions are defined relative to the piece's bounding box.
 *
 * The piece is constructed using an initial position and an array of Icon
 * objects to define the appearance of each cell.
 *
 * Subclasses extending this class must call setCells(Cell[]) to initialize the
 * cell positions and icons.
 *
 * @author Ashfaque_Bhuiyan
 */
public class RotatingSPiece extends BBB {

	/**
	 * Constructs a Rotating S-shaped piece with the given initial position and
	 * icons.
	 *
	 * @param position the initial position for the upper-left corner of the bounding box.
	 * @param icons    an array of four Icon objects representing the visual appearance of each cell.
	 * 
	 * @throws IllegalArgumentException if the length of the icons array is not 4.
	 */
	protected RotatingSPiece(Position position, Icon[] icons) {
		super(position);

		if (icons.length != 4) {
			throw new IllegalArgumentException("RotatingSPiece requires exactly 4 Icons");
		}

		// Initialize the cells that form the Rotating S-shaped piece
		Cell[] cells = new Cell[4];
		cells[0] = new Cell(icons[0], new Position(0, 0));
		cells[1] = new Cell(icons[1], new Position(0, 1));
		cells[2] = new Cell(icons[2], new Position(1, 1));
		cells[3] = new Cell(icons[3], new Position(1, 2));

		setCells(cells);
	}
}
