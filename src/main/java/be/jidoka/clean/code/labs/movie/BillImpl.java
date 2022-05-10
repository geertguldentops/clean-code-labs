package be.jidoka.clean.code.labs.movie;

import java.time.DayOfWeek;

public class BillImpl implements Bill {

	private static final double STUDENT_PRICE = 8;
	private static final double CHILDREN_PRICE = 5.5;
	private static final double SENIOR_PRICE = 6;
	private static final int GENERAL_ADMISSION_PRICE = 11;

	private double totalPrice;

	@Override
	public void startPurchase(int runtime, DayOfWeek dayOfWeek, boolean loge, boolean threeD) {
		// useless comment
	}

	@Override
	public void addTicket(int age, boolean student) {
		if (student) {
			totalPrice += STUDENT_PRICE;
			return;
		}

		if (age < 13) {
			totalPrice += CHILDREN_PRICE;
		} else if (age >= 65) {
			totalPrice += SENIOR_PRICE;
		} else {
			totalPrice += GENERAL_ADMISSION_PRICE;
		}
	}

	@Override
	public double finishPurchase() {
		return totalPrice;
	}

}
