package library;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public abstract class AbstractItem implements Item {
	protected String title;
	protected Date dueDate;
	protected Patron checkedOutTo;

	public AbstractItem(String givenTitle) {
		title = givenTitle;
		dueDate = null;
		checkedOutTo = null;
	}

	@Override
	public void checkOut(Patron p, Date now) {
		// Specific checkOut logic will be implemented in subclasses if necessary
	}

	@Override
	public void checkIn() {
		checkedOutTo = null;
		dueDate = null;
	}

	@Override
	public void renew(Date now) {
		// Specific renew logic will be implemented in subclasses if necessary
	}

	@Override
	public boolean isCheckedOut() {
		return checkedOutTo != null;
	}

	@Override
	public Date getDueDate() {
		return dueDate;
	}

	@Override
	public Patron getPatron() {
		return checkedOutTo;
	}

	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public int compareTo(Item other) {
		return this.title.compareTo(other.getTitle());
	}

	public abstract class Book extends AbstractItem {
		private static final int CHECK_OUT_DAYS = 21;
		private static final double FINE_RATE = 0.25;
		private int renewalCount;

		public Book(String givenTitle) {
			super(givenTitle);
			renewalCount = 0;
		}

		@Override
		public void checkOut(Patron p, Date now) {
			if (!isCheckedOut()) {
				checkedOutTo = p;
				GregorianCalendar cal = new GregorianCalendar();
				cal.setTime(now);
				cal.add(Calendar.DAY_OF_YEAR, CHECK_OUT_DAYS);
				cal.set(Calendar.HOUR_OF_DAY, 23);
				cal.set(Calendar.MINUTE, 59);
				cal.set(Calendar.SECOND, 59);
				dueDate = cal.getTime();
			}
		}

		@Override
		public void renew(Date now) {
			if (isCheckedOut() && renewalCount < 2 && !isOverdue(now)) {
				renewalCount++;
				GregorianCalendar cal = new GregorianCalendar();
				cal.setTime(dueDate);
				cal.add(Calendar.DAY_OF_YEAR, CHECK_OUT_DAYS);
				dueDate = cal.getTime();
			}
		}

		@Override
		public double getFine(Date now) {
			if (isOverdue(now)) {
				long diffInMillis = now.getTime() - dueDate.getTime();
				long daysLate = diffInMillis / (24 * 60 * 60 * 1000);
				return daysLate * FINE_RATE;
			}
			return 0;
		}
	}
}
