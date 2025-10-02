package lab8;

import java.util.NoSuchElementException;

/**
 * Subclass of IntList that guarantees that the elements are always in ascending
 * order.
 */
public class IntListSorted extends IntList {
	/**
	 * Constructs an empty list.
	 */
	public IntListSorted() {
		super();
	}

	/**
	 * Adds a new item to this list, inserting it so that the list remains sorted.
	 */
	@Override
	public void add(int newItem) {
		if (size() == 0 || get(size() - 1) <= newItem) {
			super.add(newItem);
		} else {
			int i = size();
			while (i > 0 && newItem < get(i - 1)) {
				i -= 1;
			}

			// now i is 0, or newItem >= list[i - 1], so put the new
			// element at position i
			super.insert(i, newItem);
		}
	}

	/**
	 * Inserts a new item in this list, inserting it so that the list remains
	 * sorted. (The given index is ignored.)
	 */
	@Override
	public void insert(int index, int newItem) {
		this.add(newItem);
	}

	/**
	 * Returns the maximum value in the sorted list, which is the last element.
	 */
	@Override
    public int getMaximum() {
        if (getSize() == 0) {
            throw new NoSuchElementException("List is empty.");
        }
        return getList()[getSize() - 1];
    }

    @Override
    public int getMinimum() {
        if (getSize() == 0) {
            throw new NoSuchElementException("List is empty.");
        }
        return getList()[0];
    }

    public int getMedian() {
        if (getSize() == 0) {
            throw new NoSuchElementException("List is empty.");
        }
        int[] sortedList = getList();
        if (getSize() % 2 == 1) {
            return sortedList[getSize() / 2];
        } else {
            return sortedList[(getSize() / 2) - 1];
        }
    }
}
