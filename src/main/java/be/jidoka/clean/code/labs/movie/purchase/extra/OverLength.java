package be.jidoka.clean.code.labs.movie.purchase.extra;

import be.jidoka.clean.code.labs.movie.purchase.ticket.Ticket;

public class OverLength implements Extra {

	private final int runtime;

	public OverLength(int runtime) {
		this.runtime = runtime;
	}

	@Override
	public boolean appliesTo(Ticket ticket) {
		return true;
	}

	@Override
	public double price() {
		return (runtime > 120) ? 1.50 : 0.00;
	}

}
