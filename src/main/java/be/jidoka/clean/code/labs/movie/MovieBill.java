package be.jidoka.clean.code.labs.movie;

import java.time.DayOfWeek;

public class MovieBill implements Bill {

	private double purchase = 0;

	@Override
	public void startPurchase(int runtime, DayOfWeek dayOfWeek, boolean loge, boolean threeD) {

	}

	@Override
	public void addTicket(int age, boolean student) {
		purchase += 11.0;
	}

	@Override
	public double finishPurchase() {
		return purchase;
	}
}
