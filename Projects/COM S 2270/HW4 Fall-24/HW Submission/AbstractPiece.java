package hw4;

import api.Cell;
import api.Icon;
import api.Piece;
import api.Position;

/**
 * Abstract superclass for implementations of the interface. This class provides
 * basic functionality for managing the position and cells of a game piece on a
 * grid.
 *
 * Subclasses must call setCells(Cell[]) to initialize the cells defining the
 * shape and icons of the piece.
 *
 * @author Ashfaque_Bhuiyan
 */
public abstract class AbstractPiece implements Piece {

	/**
	 * Position of this piece on the grid.
	 */
	private Position position;

	/**
	 * Cells of this piece relative to the position.
	 */
	private Cell[] cells;

	/**
	 * Constructs a piece with the given position.
	 *
	 * @param position the initial position for the upper-left corner of the
	 *                 bounding box.
	 */
	protected AbstractPiece(Position position) {
		this.position = position;
	}

	@Override
	public Position getPosition() {
		return position;
	}

	/**
	 * This method returns a deep copy of the cells to ensure encapsulation.
	 */
	@Override
	public Cell[] getCells() {
		Cell[] cellsCopy = new Cell[cells.length];
		for (int i = 0; i < cells.length; i++) {
			cellsCopy[i] = new Cell(cells[i]);
		}
		return cellsCopy;
	}

	/**
	 * This method calculates the absolute positions of the cells on the grid based
	 * on the current position of the piece.
	 */
	@Override
	public Cell[] getCellsAbsolute() {
		Cell[] absoluteCells = new Cell[cells.length];
		for (int i = 0; i < cells.length; i++) {
			int row = cells[i].getRow() + position.row();
			int col = cells[i].getCol() + position.col();
			absoluteCells[i] = new Cell(cells[i].getIcon(), new Position(row, col));
		}
		return absoluteCells;
	}

	@Override
	public void setCells(Cell[] givenCells) {
		cells = givenCells;
	}

	/**
	 * Shifts the piece one column to the right.
	 */
	@Override
	public void shiftRight() {
		position = new Position(position.row(), position.col() + 1);
	}

	/**
	 * Shifts the piece one column to the left.
	 */
	@Override
	public void shiftLeft() {
		position = new Position(position.row(), position.col() - 1);
	}

	/**
	 * Shifts the piece one row down.
	 */
	@Override
	public void shiftDown() {
		position = new Position(position.row() + 1, position.col());
	}

	/**
	 * Transforms the cells vertically for specific piece types, such as L-shaped or
	 * T-shaped pieces. This updates the relative column positions of the cells.
	 */
	@Override
	public void transform() {
		for (Cell cell : cells) {
			int newCol = 2 - cell.getCol();
			cell.setRowCol(cell.getRow(), newCol);
		}
	}

	/**
	 * Creates a deep copy of this piece, including its cells, ensuring the clone is
	 * independent of the original.
	 */
	@Override
	public Piece clone() {
		try {
			AbstractPiece cloned = (AbstractPiece) super.clone();
			cloned.cells = new Cell[cells.length];
			for (int i = 0; i < cells.length; i++) {
				cloned.cells[i] = new Cell(cells[i]);
			}
			return cloned;
		} catch (CloneNotSupportedException e) {
			// Should never occur since this class implements Cloneable
			throw new AssertionError("Clone not supported", e);
		}
	}

	/**
	 * Cycles the icons of the cells in the piece, shifting each icon to the next
	 * cell in the array. The icon in the last cell is moved to the first cell.
	 */
	@Override
	public void cycle() {
		if (cells.length > 1) {
			Icon lastIcon = cells[cells.length - 1].getIcon();
			for (int i = cells.length - 1; i > 0; i--) {
				cells[i].setIcon(cells[i - 1].getIcon());
			}
			cells[0].setIcon(lastIcon);
		}
	}
}
