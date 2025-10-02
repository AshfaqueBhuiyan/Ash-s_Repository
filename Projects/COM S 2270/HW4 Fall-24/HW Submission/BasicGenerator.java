package hw4;

import java.util.Random;
import api.Generator;
import api.Icon;
import api.Piece;
import api.Position;
import examples.SamplePiece;

/**
 * Generator for link Piece objects in BlockAddiction. This class generates
 * pieces with random types and icons based on predefined probabilities. The
 * initial position of each piece depends on its size and the grid width.
 *
 * Icons are chosen uniformly at random from the available colors.
 *
 * The column position of each piece is initialized to width / 2 - 1, and the
 * row position is typically set to -2 for pieces with a 3x3 bounding box.
 *
 * @author Ashfaque_Bhuiyan
 */
public class BasicGenerator implements Generator {

	/**
	 * Random number generator for selecting piece types and icons.
	 */
	private final Random rand;

	/**
	 * Constructs a BasicGenerator that initializes a new link Random instance as
	 * its source of randomness.
	 */
	public BasicGenerator() {
		rand = new Random();
	}

	/**
	 * Generates a new link Piece object with a random type and initial position.
	 *
	 * @param width the width of the grid
	 * 
	 * @return a randomly generated link Piece
	 */
	@Override
	public Piece getNext(int width) {
		int col = width / 2 - 1;
		int probability = rand.nextInt(100);

		// Default return for a single piece type (e.g., SnakingPiece)
		return new SnakingPiece(new Position(-1, col), getBlocks(4));
	}

	/**
	 * Generates a random link Icon by selecting a color uniformly at random from
	 * the available colors.
	 *
	 * @return a randomly generated link Icon
	 */
	@Override
	public Icon randomIcon() {
		return new Icon(Icon.COLORS[rand.nextInt(Icon.COLORS.length)]);
	}

	/**
	 * Generates an array of random link Icon objects.
	 *
	 * @param num the number of icons to generate
	 * 
	 * @return an array of random link Icon objects
	 */
	private Icon[] getBlocks(int num) {
		return java.util.stream.IntStream.range(0, num).mapToObj(i -> randomIcon()).toArray(Icon[]::new);
	}
}
