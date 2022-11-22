package be.jidoka.clean.code.labs.movie.purchase.extra;

import be.jidoka.clean.code.labs.movie.purchase.ticket.GroupTicket;
import be.jidoka.clean.code.labs.movie.purchase.ticket.Ticket;

import java.time.DayOfWeek;

public class SpecialMovieDay implements Extra {

    private final DayOfWeek dayOfWeek;

    public SpecialMovieDay(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    @Override
    public boolean appliesTo(Ticket ticket) {
        return !(ticket instanceof GroupTicket);
    }

    @Override
    public double price() {
        return (DayOfWeek.THURSDAY == dayOfWeek) ? -2.00 : 0.00;
    }

}
