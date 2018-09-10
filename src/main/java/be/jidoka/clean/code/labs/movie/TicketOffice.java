package be.jidoka.clean.code.labs.movie;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

public class TicketOffice implements Bill {

    private List<Ticket> tickets = new ArrayList<>();

    @Override
    public void startPurchase(int runtime, DayOfWeek dayOfWeek, boolean loge, boolean threeD) {

    }

    @Override
    public void addTicket(int age, boolean student) {
        Ticket newTicket = new Ticket(student);
        tickets.add(newTicket);
    }

    @Override
    public double finishPurchase() {
        double total = 0.0;

        for (Ticket ticket : tickets) {
            total += ticket.calculatePrice();
        }

        return total;
    }
}
