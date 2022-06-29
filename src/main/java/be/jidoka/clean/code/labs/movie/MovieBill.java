package be.jidoka.clean.code.labs.movie;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

public class MovieBill implements Bill {

	private final List<Ticket> tickets = new ArrayList<>();

	@Override
	public void startPurchase(int runtime, DayOfWeek dayOfWeek, boolean loge, boolean threeD) {

	}

	@Override
	public void addTicket(int age, boolean student) {
		tickets.add(new Ticket(age, student));
	}

	@Override
	public double finishPurchase() {
		if (tickets.size() < 20) {
			return calculate(tickets);
		} else {
			return 6.0 * tickets.size();
		}
	}

	private double calculate(List<Ticket> tickets) {
		double purchase = 0;
		for (Ticket ticket : tickets) {
			if (ticket.age >= 65) {
				purchase += 6.0;
			} else if (ticket.student) {
				purchase += 8.0;

			} else {
				purchase += 11.0;
			}
		}
		return purchase;
	}

	private static class Ticket {

		private final int age;
		private final boolean student;

		public Ticket(int age, boolean student) {
			this.age = age;
			this.student = student;
		}
	}

}
