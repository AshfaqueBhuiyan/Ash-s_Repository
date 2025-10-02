package hw4;

import java.util.ArrayList;

import api.Cell;
import api.Position;

/**
 * Represents a piece with a circling movement pattern in the game. The piece
 * moves its cells sequentially through a predefined set of positions in a
 * cyclic order, creating a circling effect.
 *
 * This class extends AbstractPiece class and implements a transformation method
 * that updates the positions of the cells based on the movement sequence.
 *
 * @author Ashfaque_Bhuiyan
 */
public class CCC extends AbstractPiece {

	/**
	 * Counter for tracking the current position in the movement sequence.
	 */
	private int counter = 0;

	/**
	 * Predefined sequence of positions representing the circling movement pattern.
	 */
	protected Position[] sequence = { new Position(0, 0), new Position(0, 1), new Position(0, 2),
			new Position(1, 2),
			new Position(2, 2), new Position(2, 1), new Position(2, 0),
			new Position(1, 0)
	};

	/**
	 * Constructs a piece with the given initial position and initializes its
	 * movement sequence.
	 *
	 * @param position the initial position for the upper-left corner of the bounding box.
	 */
	protected CCC(Position position) {
		super(position);
	}

	/**
	 * This method updates the positions of the cells based on the predefined
	 * movement sequence. The first cell leads the sequence, and the remaining cells
	 * follow the positions of the preceding cells.
	 */
	@Override
	public void transform() {
		counter++; // Increment the counter on each transformation call
		Cell[] cells = getCells();

		// Store old positions to update trailing cells
		ArrayList<Position> oldPositions = new ArrayList<>();
		for (Cell cell : cells) {
			oldPositions.add(new Position(cell.getRow(), cell.getCol()));
		}

		// Updates the position of the head cell
		cells[0].setPosition(sequence[counter % sequence.length]);

		// Updates all other cells to follow the previous cell's old position
		for (int i = 1; i < cells.length; i++) {
			cells[i].setPosition(oldPositions.get(i - 1));
		}

		setCells(cells);
	}
}
