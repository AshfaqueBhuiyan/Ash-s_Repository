package hw4;

import api.AbstractGame;
import api.Generator;
import api.Position;
import api.Icon;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Represents a specialized game variant called Block Addiction. In this game,
 * collapsible positions are determined based on matching adjacent icons, with
 * at least three matching icons required to form a collapsible set.
 * 
 * The game also supports pre-filled rows during initialization, where blocks
 * are placed in a checkered pattern based on a given number of rows to
 * pre-fill.
 * 
 * This class extends AbstractGame class and provides customized logic for
 * collapsing positions.
 * 
 * @author Ashfaque_Bhuiyan
 */
public class BlockAddiction extends AbstractGame {

	/**
	 * Constructs a BlockAddiction game with a pre-filled grid.
	 * 
	 * @param height      the height of the grid.
	 * @param width       the width of the grid.
	 * @param gen         the Generator used to create random icons.
	 * @param preFillRows the number of rows to pre-fill with blocks in a checkered pattern.
	 */
	public BlockAddiction(int height, int width, Generator gen, int preFillRows) {
		super(height, width, gen);
		IntStream.range(0, preFillRows).forEach(i -> IntStream.range(0, width).forEach(j -> {
			if ((i + j) % 2 == 0) {
				setBlock(height - 1 - i, j, gen.randomIcon());
			}
		}));
	}

	/**
	 * Constructs a BlockAddiction game without pre-filled rows.
	 * 
	 * @param height the height of the grid.
	 * @param width  the width of the grid.
	 * @param gen    the link Generator used to create random icons.
	 */
	public BlockAddiction(int height, int width, Generator gen) {
		super(height, width, gen);
	}

	/**
	 * Determines all positions in the grid that form a collapsible set based on
	 * matching adjacent icons. A position is collapsible if it has at least two
	 * directly adjacent icons (horizontally or vertically) that match its own icon.
	 * The method also includes the adjacent matching icons in the collapsible set.
	 * 
	 * @return a sorted list of Position objects representing the positions to collapse.
	 */
	@Override
	public List<Position> determinePositionsToCollapse() {
		List<Position> positions = new ArrayList<>();

		IntStream.range(0, getHeight()).forEach(row -> IntStream.range(0, getWidth()).forEach(col -> {
			Icon thisIcon = getIcon(row, col);
			if (thisIcon == null) {
				return;
			}

			List<Position> matchers = new ArrayList<>();

			long numNeighbors = IntStream.rangeClosed(-1, 1).boxed()
					.flatMap(p -> IntStream.rangeClosed(-1, 1).mapToObj(q -> new int[] { p, q }))
					.filter(offset -> (offset[0] == 0 && offset[1] != 0) || (offset[1] == 0 && offset[0] != 0))
					.filter(offset -> {
						int nrow = row + offset[0];
						int ncol = col + offset[1];
						if (nrow >= 0 && nrow < getHeight() && ncol >= 0 && ncol < getWidth()) {
							Icon otherIcon = getIcon(nrow, ncol);
							if (thisIcon.matches(otherIcon)) {
								matchers.add(new Position(nrow, ncol));
								return true;
							}
						}
						return false;
					}).count();

			if (numNeighbors >= 2) {
				Position newPos = new Position(row, col);
				if (!positions.contains(newPos)) {
					positions.add(newPos);
				}
				matchers.stream().filter(neiPos -> !positions.contains(neiPos)).forEach(positions::add);
			}
		}));

		Collections.sort(positions);
		return positions;
	}
}
