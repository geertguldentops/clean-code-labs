package be.jidoka.clean.code.labs.movie.purchase.ticket;

public class ChildTicket extends Ticket {

	@Override
	public double basePrice() {
		return 5.5;
	}

}
