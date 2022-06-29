package be.jidoka.clean.code.labs.movie;

import java.time.DayOfWeek;

public class MovieBill implements Bill {

	private double purchase = 0;
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
			purchase = 11.0 * count;
		} else {
			purchase = 6.0 * count;
		}

		return purchase;
	}
}
