package be.jidoka.clean.code.labs.movie.purchase.ticket;

public class AdultTicket extends Ticket {

	@Override
	public double basePrice() {
		return 11.0;
	}

}
