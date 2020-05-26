package be.jidoka.clean.code.labs.movie;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

public class Calculator implements Bill {

	private final List<Double> ticketPrices = new ArrayList<>();
	private double extraPrice = 0.0;

	@Override
	public void startPurchase(int runtime, DayOfWeek dayOfWeek, boolean loge, boolean threeD) {
		if (threeD) {
			extraPrice += 3.0;
		}
	}

	@Override
	public void addTicket(int age, boolean student) {
		if (student) {
			ticketPrices.add(8.0 + extraPrice);
		} else {
			ticketPrices.add(11.0 + extraPrice);
		}
	}

	@Override
	public double finishPurchase() {
		double sum = 0.0;

		for (Double ticketPrice : ticketPrices) {
			sum += ticketPrice;
		}

		return sum;
	}
}
