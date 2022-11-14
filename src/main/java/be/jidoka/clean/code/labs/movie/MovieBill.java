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

	private double calculatePrice(Ticket ticket) {
		if (ticket.getAge() >= 65) {
			return 6.00;
		} else if (ticket.getAge() < 13) {
			return 5.50;
		} else {
			if (ticket.isStudent()) {
				return 8.00;
			} else {
				return 11.00;
			}
		}
	}

	@Override
	public double finishPurchase() {
		return tickets.stream()
				.mapToDouble(this::calculatePrice)
				.sum();
	}
}
