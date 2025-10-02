package hw4;

import api.Position;

/**
 * Represents a piece with a snaking movement pattern in the game. This piece moves its cells
 * sequentially through a predefined set of positions, forming a snaking motion as it cycles
 * through the sequence.
 * 
 * This class extends CCC class and overrides the movement sequence with a custom snake pattern.
 * It also uses a helper method to dynamically generate the snaking sequence for better clarity
 * and maintainability.
 * 
 * @author Ashfaque_Bhuiyan
 */
public class DDD extends CCC {

    /**
     * Predefined sequence of positions representing the snaking movement pattern.
     * This sequence is generated dynamically using createSnakeSequence().
     */
    private static final Position[] SNAKE_SEQUENCE = createSnakeSequence();

    /**
     * Constructs a {@code DDD} piece with the specified initial position and assigns
     * the predefined snaking sequence as its movement pattern.
     * 
     * @param position The initial position for the upper-left corner of the bounding box.
     */
    protected DDD(Position position) {
        super(position);
        sequence = SNAKE_SEQUENCE;
    }

    /**
     * Generates the sequence of positions for the snaking movement pattern.
     * The sequence defines the positions the piece will cycle through during its movement.
     * 
     * @return An array of Position objects representing the snaking movement pattern.
     */
    private static Position[] createSnakeSequence() {
        return new Position[] {
            new Position(0, 0), new Position(0, 1), new Position(0, 2),
            new Position(1, 2), new Position(1, 1), new Position(1, 0),
            new Position(2, 0), new Position(2, 1), new Position(2, 2),
            new Position(1, 2), new Position(1, 1), new Position(1, 0)
        };
    }
}
