package hw4;

import api.Cell;
import api.Position;

/**
 * Represents a generic abstract piece in the game with basic functionality for
 * vertical transformation.
 *
 * This class extends AbstractPiece and implements a transformation method that
 * vertically flips the cells of the piece based on their column positions.
 *
 * @author Ashfaque_Bhuiyan
 */
public class AAA extends AbstractPiece {

	/**
	 * Constructs an instance of AAA with the specified initial position.
	 *
	 * @param position the initial position for the upper-left corner of the bounding box.
	 */
	protected AAA(Position position) {
		super(position);
	}

	/**
	 * This method applies a vertical transformation to the piece, flipping the
	 * cells horizontally within the bounding box. Each cell's column position is
	 * updated based on the transformation logic.
	 */
	@Override
	public void transform() {
		Cell[] cells = getCells();

		for (Cell cell : cells) {
			// Flip the column position of the cell within a dynamic bounding box size
			int boundingBoxWidth = 3; // Assuming a 3-column bounding box for the transformation
			int newCol = (boundingBoxWidth - 1) - cell.getCol();
			cell.setRowCol(cell.getRow(), newCol);
		}

		setCells(cells);
	}
}
