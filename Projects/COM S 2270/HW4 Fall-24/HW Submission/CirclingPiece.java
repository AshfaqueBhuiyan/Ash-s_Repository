package hw4;

import api.Cell;
import api.Icon;
import api.Position;
import java.lang.IllegalArgumentException;

/**
 * Represents a CirclingPiece in the game. This piece is composed of four cells arranged in a specific pattern.
 * The piece uses the movement logic defined in its superclass CCC class, allowing it to cycle through predefined positions.
 * 
 * This class initializes the piece with a specific arrangement of cells and validates the input to ensure the correct number of icons.
 * 
 * @author Ashfaque_Bhuiyan
 */
public class CirclingPiece extends CCC {

    /**
     * Constructs a CirclingPiece with the specified initial position and icons.
     * The piece consists of four cells arranged in an "L" shape.
     * 
     * @param position the initial position for the upper-left corner of the bounding box.
     * @param icons an array of four link Icon objects representing the appearance of each cell.
     * 
     * @throws IllegalArgumentException if the length of the Icons array is not 4.
     */
    protected CirclingPiece(Position position, Icon[] icons) {
        super(position);

        if (icons.length != 4) {
            throw new IllegalArgumentException("CirclingPiece requires exactly 4 Icons");
        }

        // Initialize the cells that form the CirclingPiece
        Cell[] cells = new Cell[4];
        cells[0] = new Cell(icons[0], new Position(0, 0));
        cells[1] = new Cell(icons[1], new Position(1, 0));
        cells[2] = new Cell(icons[2], new Position(2, 0));
        cells[3] = new Cell(icons[3], new Position(2, 1));
        
        setCells(cells);
    }
}
