package hw4;

import api.Position;
import api.Cell;
import api.Icon;

/**
 * Represents a Quotes-shaped piece in the game. This piece consists of four cells arranged
 * in a configuration resembling a pair of quotation marks. The specific positions are defined
 * relative to the piece's bounding box.
 *
 * The piece is constructed using an initial position and an array of Icon objects
 * to define the appearance of each cell.
 *
 * Subclasses extending this class must call setCells(Cell[]) to initialize the
 * cell positions and icons.
 *
 * @author Ashfaque_Bhuiyan
 */
public class QuotesPiece extends BBB {

    /**
     * Constructs a Quotes-shaped piece with the given initial position and icons.
     *
     * @param position the initial position for the upper-left corner of the bounding box.
     * @param icons an array of four Icon objects representing the visual appearance of each cell.
     * 
     * @throws IllegalArgumentException if the length of the icons array is not 4.
     */
    protected QuotesPiece(Position position, Icon[] icons) {
        super(position);

        if (icons.length != 4) {
            throw new IllegalArgumentException("QuotesPiece requires exactly 4 Icons");
        }

        // Initialize the cells that form the Quotes-shaped piece
        Cell[] cells = new Cell[4];
        cells[0] = new Cell(icons[0], new Position(0, 0));
        cells[1] = new Cell(icons[1], new Position(1, 0));
        cells[2] = new Cell(icons[2], new Position(0, 2));
        cells[3] = new Cell(icons[3], new Position(1, 2));

        setCells(cells);
    }
}
