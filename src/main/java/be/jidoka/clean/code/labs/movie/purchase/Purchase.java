package be.jidoka.clean.code.labs.movie.purchase;

import be.jidoka.clean.code.labs.movie.Bill;
import be.jidoka.clean.code.labs.movie.purchase.extra.Extra;
import be.jidoka.clean.code.labs.movie.purchase.extra.OverLength;
import be.jidoka.clean.code.labs.movie.purchase.extra.SpecialMovieDay;
import be.jidoka.clean.code.labs.movie.purchase.extra.ThreeDMovie;
import be.jidoka.clean.code.labs.movie.purchase.ticket.Ticket;
import be.jidoka.clean.code.labs.movie.purchase.tickets.Tickets;

import java.time.DayOfWeek;
import java.util.HashSet;
import java.util.Set;

public class Purchase implements Bill {

    private Set<Extra> extras = new HashSet<>();
    private Set<Ticket> tickets = new HashSet<>();

    @Override
    public void startPurchase(int runtime, DayOfWeek dayOfWeek, boolean loge, boolean threeD) {
        extras.add(new OverLength(runtime));
        extras.add(new SpecialMovieDay(dayOfWeek));
        extras.add(new ThreeDMovie(threeD));
        extras.add(new Weekend(dayOfWeek));
        extras.add(new Balcony(loge));
    }

    @Override
    public void addTicket(int age, boolean student) {
        tickets.add(Ticket.create(age, student));
    }

    @Override
    public double finishPurchase() {
        return Tickets.create(tickets).calculatePrice(extras);
    }

}
