package be.jidoka.clean.code.labs.movie.purchase.tickets;

import be.jidoka.clean.code.labs.movie.purchase.extra.Extra;
import be.jidoka.clean.code.labs.movie.purchase.ticket.Ticket;

import java.util.Set;

import static java.util.stream.Collectors.toUnmodifiableSet;
import static java.util.stream.IntStream.rangeClosed;

public class Tickets {

    private final Set<Ticket> tickets;

    public static Tickets create(Set<Ticket> tickets) {
        return new Tickets(tickets);
    }

    public Tickets(Set<Ticket> tickets) {
        if (tickets.size() >= 20) {
            this.tickets = rangeClosed(1, tickets.size())
                    .mapToObj(i -> Ticket.groupTicket())
                    .collect(toUnmodifiableSet());
        } else {
            this.tickets = tickets;
        }
    }

    public double calculatePrice(Set<Extra> extras) {
        return this.tickets.stream()
                .mapToDouble(ticket -> ticket.calculateTotalPrice(extras))
                .sum();
    }

}
