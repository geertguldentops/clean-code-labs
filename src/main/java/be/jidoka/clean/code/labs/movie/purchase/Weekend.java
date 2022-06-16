package be.jidoka.clean.code.labs.movie.purchase;

import be.jidoka.clean.code.labs.movie.purchase.extra.Extra;
import be.jidoka.clean.code.labs.movie.purchase.ticket.Ticket;

import java.time.DayOfWeek;
import java.util.EnumSet;

import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;

public class Weekend implements Extra {

	private final DayOfWeek dayOfWeek;

	public Weekend(DayOfWeek dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	@Override
	public boolean appliesTo(Ticket ticket) {
		return true;
	}

	@Override
	public double price() {
		return EnumSet.of(SATURDAY, SUNDAY).contains(dayOfWeek) ? 1.50 : 0.00;
	}

}
