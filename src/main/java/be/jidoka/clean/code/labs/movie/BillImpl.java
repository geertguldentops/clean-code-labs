package be.jidoka.clean.code.labs.movie;

import java.time.DayOfWeek;

public class BillImpl implements Bill {

	private int totalPrice;

	@Override
	public void startPurchase(int runtime, DayOfWeek dayOfWeek, boolean loge, boolean threeD) {
		// useless comment
	}

	@Override
	public void addTicket(int age, boolean student) {
		if (age >= 65) {
			totalPrice += 6;
		} else if (student) {
			totalPrice += 8;
		} else {
			totalPrice += 11;
		}
	}

	@Override
	public double finishPurchase() {
		return totalPrice;
	}

}
