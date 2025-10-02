package hw3;

import static api.Direction.*;

import java.util.ArrayList;
import api.BodySegment;
import api.Cell;
import api.Direction;

/**
 * Represents a lizard as a collection of body segments on a grid. This class
 * allows for manipulation and querying of the lizard's position and orientation
 * on the grid. The segments are ordered from tail to head.
 * 
 * @see api.BodySegment
 * @see LizardGame#move(int, int, api.Direction)
 * @see api.Direction
 * @see LizardGame#getSegmentAt(api.Cell)
 * 
 * @author Ashfaque_Bhuiyan
 */
public class Lizard {
	private ArrayList<BodySegment> segments;

	/**
	 * Constructs a Lizard object.
	 * A list of body segments representing the lizard, ordered from tail to head.
	 */
	public Lizard() {
		this.segments = new ArrayList<>();
	}

	/**
	 * Sets the segments of the lizard. Segments should be ordered from tail to
	 * head.
	 * 
	 * @param segments list of segments ordered from tail to head
	 */
	public void setSegments(ArrayList<BodySegment> segments) {
		this.segments = segments;
	}

	/**
	 * Gets the segments of the lizard. Segments are ordered from tail to head.
	 * 
	 * @return a list of segments ordered from tail to head
	 */
	public ArrayList<BodySegment> getSegments() {
		return segments;
	}

	/**
	 * Gets the head segment of the lizard. Returns null if the segments have not
	 * been initialized or there are no segments.
	 * 
	 * @return the head segment
	 */
	public BodySegment getHeadSegment() {
		if (segments.isEmpty()) {
			return null;
		}
		return segments.get(segments.size() - 1);
	}

	/**
	 * Gets the tail segment of the lizard. Returns null if the segments have not
	 * been initialized or there are no segments.
	 * 
	 * @return the tail segment
	 */
	public BodySegment getTailSegment() {
		if (segments.isEmpty()) {
			return null;
		}
		return segments.get(0);
	}

	/**
	 * Gets the segment that is located at a given cell or null if there is no
	 * segment at that cell.
	 * 
	 * @param cell to look for lizard
	 * 
	 * @return the segment that is on the cell or null if there is none
	 */
	public BodySegment getSegmentAt(Cell cell) {
		for (BodySegment segment : segments) {
			if (segment.getCell().equals(cell)) {
				return segment;
			}
		}
		return null;
	}

	/**
	 * Get the segment that is in front of (closer to the head segment than) the
	 * given segment. Returns null if there is no segment ahead.
	 * 
	 * @param segment the starting segment
	 * 
	 * @return the segment in front of the given segment or null
	 */
	public BodySegment getSegmentAhead(BodySegment segment) {
		int index = segments.indexOf(segment);
		if (index < 0 || index == segments.size() - 1) {
			return null;
		}
		return segments.get(index + 1);
	}

	/**
	 * Get the segment that is behind (closer to the tail segment than) the given
	 * segment. Returns null if there is not segment behind.
	 * 
	 * @param segment the starting segment
	 * 
	 * @return the segment behind of the given segment or null
	 */
	public BodySegment getSegmentBehind(BodySegment segment) {
		int index = segments.indexOf(segment);
		if (index <= 0) {
			return null;
		}
		return segments.get(index - 1);
	}

	/**
	 * Gets the direction from the perspective of the given segment pointing to the
	 * segment ahead of it. Returns null if there is no segment ahead.
	 * 
	 * @param segment the starting segment
	 * 
	 * @return the direction to the segment ahead of the given segment or null
	 */
	public Direction getDirectionToSegmentAhead(BodySegment segment) {
		BodySegment segmentAhead = getSegmentAhead(segment);
		if (segmentAhead == null) {
			return null;
		}
		return determineDirection(segment.getCell(), segmentAhead.getCell());
	}

	/**
	 * Gets the direction from the perspective of the given segment pointing to the
	 * segment behind it. Returns null if there is no segment behind.
	 * 
	 * @param segment the starting segment
	 * 
	 * @return the direction to the segment behind of the given segment or null
	 */
	public Direction getDirectionToSegmentBehind(BodySegment segment) {
		BodySegment segmentBehind = getSegmentBehind(segment);
		if (segmentBehind == null) {
			return null;
		}
		return determineDirection(segment.getCell(), segmentBehind.getCell());
	}

	/**
	 * Gets the direction in which the head segment is pointing. This is the
	 * direction formed by going from the segment behind the head segment to the
	 * head segment. A lizard that does not have more than one segment has no
	 * defined head direction and returns null.
	 * 
	 * @return the direction in which the head segment is pointing or null
	 */
	public Direction getHeadDirection() {
		if (segments.size() < 2) {
			return null;
		}
		return determineDirection(segments.get(segments.size() - 2).getCell(), getHeadSegment().getCell());
	}

	/**
	 * Gets the direction in which the tail segment is pointing. This is the
	 * direction formed by going from the segment ahead of the tail segment to the
	 * tail segment. A lizard that does not have more than one segment has no
	 * defined tail direction and returns null.
	 * 
	 * @return the direction in which the tail segment is pointing or null
	 */
	public Direction getTailDirection() {
		if (segments.size() < 2) {
			return null;
		}
		return determineDirection(getSegmentAhead(segments.get(0)).getCell(), getTailSegment().getCell());
	}

	/**
	 * Helper method to determine the direction between two cells.
	 * 
	 * @param from The starting cell.
	 * @param to   The destination cell.
	 * 
	 * @return The direction from the first cell to the second cell.
	 */
	private Direction determineDirection(Cell from, Cell to) {
		if (from.getCol() == to.getCol()) {
			if (from.getRow() < to.getRow()) {
				return DOWN;
			} else {
				return UP;
			}
		} else if (from.getRow() == to.getRow()) {
			if (from.getCol() < to.getCol()) {
				return RIGHT;
			} else {
				return LEFT;
			}
		}
		return null;
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		for (BodySegment seg : getSegments()) {
			result.append(seg).append(" ");
		}
		return result.toString().trim();
	}
}
