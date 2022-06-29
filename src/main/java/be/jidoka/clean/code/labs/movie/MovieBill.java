package be.jidoka.clean.code.labs.movie;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

public class MovieBill implements Bill {

	private List<Ticket> tickets = new ArrayList<>();

	@Override
	public void startPurchase(int runtime, DayOfWeek dayOfWeek, boolean loge, boolean threeD) {

	}

	@Override
	public void addTicket(int age, boolean student) {
		tickets.add(new Ticket());
	}

	@Override
	public double finishPurchase() {
		if (tickets.size() < 20) {
			return 11.0 * tickets.size();
		} else {
			return 6.0 * tickets.size();
		}
	}

	private static class Ticket {

	}

}
