package be.jidoka.clean.code.labs.movie.purchase;

import be.jidoka.clean.code.labs.movie.purchase.extra.Extra;
import be.jidoka.clean.code.labs.movie.purchase.ticket.Ticket;

public class Balcony implements Extra {

	private final boolean loge;

	public Balcony(boolean loge) {
		this.loge = loge;
	}

	@Override
	public boolean appliesTo(Ticket ticket) {
		return true;
	}

	@Override
	public double price() {
		return loge ? 2.00 : 0.00;
	}

}
