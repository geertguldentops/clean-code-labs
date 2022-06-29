package be.jidoka.clean.code.labs.movie;

import java.time.DayOfWeek;

public class MovieBill implements Bill {

	private int count = 0;

	@Override
	public void startPurchase(int runtime, DayOfWeek dayOfWeek, boolean loge, boolean threeD) {

	}

	@Override
	public void addTicket(int age, boolean student) {
		count++;
	}

	@Override
	public double finishPurchase() {
		if (count < 20) {
			return 11.0 * count;
		} else {
			return 6.0 * count;
		}
	}
}
