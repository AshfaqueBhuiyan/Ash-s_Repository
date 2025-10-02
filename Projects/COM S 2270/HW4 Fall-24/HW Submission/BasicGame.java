package hw4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

import api.AbstractGame;
import api.Position;

/**
 * Represents a basic implementation of a game derived from AbstractGame class.
 * In this game, a "collapsible set" is formed by filling an entire row, similar
 * to the mechanics of the traditional game of Tetris. Unlike Tetris, the colors
 * of the icons do not need to match.
 * 
 * This class defines specific behavior for determining collapsible positions
 * and checking whether a row is fully filled.
 * 
 * @author Ashfaque_Bhuiyan
 */
public class BasicGame extends AbstractGame {

	/**
	 * Constructs a BasicGame class with an 8x10 grid and uses BasicGenerator class
	 * to generate new pieces.
	 */
	public BasicGame() {
		super(8, 10, new BasicGenerator());
	}

	/**
	 * This method identifies all positions in the grid that are part of a
	 * collapsible set. A collapsible set in this game consists of all positions in
	 * any row that is completely filled with non-null icons.
	 * 
	 * @return a sorted list of Position objects representing the positions to collapse.
	 */
	@Override
	public List<Position> determinePositionsToCollapse() {
		List<Position> positions = new ArrayList<>();

		IntStream.range(0, getHeight()).filter(this::isRowFilled).forEach(
				row -> IntStream.range(0, getWidth()).mapToObj(col -> new Position(row, col)).forEach(positions::add));

		// Sort the positions
		Collections.sort(positions);
		return positions;
	}

	/**
	 * Determines whether the given row in the grid is completely filled. A row is
	 * considered filled if all of its icons are non-null.
	 * 
	 * @param row the row index to check.
	 * 
	 * @return true if the row is completely filled, false otherwise.
	 */
	private boolean isRowFilled(int row) {
		return IntStream.range(0, getWidth()).allMatch(col -> getIcon(row, col) != null);
	}
}
