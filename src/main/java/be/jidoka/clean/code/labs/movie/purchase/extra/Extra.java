package be.jidoka.clean.code.labs.movie.purchase.extra;

import be.jidoka.clean.code.labs.movie.purchase.ticket.Ticket;

public interface Extra {

	boolean appliesTo(Ticket ticket);

	double price();

}
