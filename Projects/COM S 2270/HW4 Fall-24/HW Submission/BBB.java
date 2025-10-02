package hw4;

import api.Cell;
import api.Position;

/**
 * Represents a generic abstract piece in the game with basic functionality for
 * clockwise rotation.
 *
 * This class extends AbstractPiece and implements a transformation method that
 * rotates the piece's cells 90 degrees clockwise within its bounding box.
 *
 * @author Ashfaque_Bhuiyan
 */
public class BBB extends AbstractPiece {

	/**
	 * Constructs an instance of BBB class with the specified initial position.
	 *
	 * @param position the initial position for the upper-left corner of the bounding box.
	 */
	protected BBB(Position position) {
		super(position);
	}

	/**
	 * This method applies a clockwise rotation to the piece, updating the row and
	 * column positions of each cell based on their original coordinates.
	 */
	@Override
	public void transform() {
		Cell[] cells = getCells();

		for (Cell cell : cells) {
			// Rotate the cell 90 degrees clockwise within a dynamic bounding box size
			int boundingBoxSize = 3; // Assuming a 3x3 bounding box for the rotation
			int newRow = cell.getCol();
			int newCol = (boundingBoxSize - 1) - cell.getRow();
			cell.setRowCol(newRow, newCol);
		}

		setCells(cells);
	}
}
