package be.jidoka.clean.code.labs.movie;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

public class InMemoryBill implements Bill {

    private static final double GROUP_PRICE = 6.0;

    private Movie movie;
    private final List<Ticket> tickets = new ArrayList<>();

    @Override
    public void startPurchase(int runtime, DayOfWeek dayOfWeek, boolean loge, boolean threeD) {
        this.movie = new Movie(runtime, dayOfWeek, loge, threeD);
    }

    @Override
    public void addTicket(int age, boolean student) {
        tickets.add(new Ticket(age, student));
    }

    @Override
    public double finishPurchase() {
        if (isGroup()) {
            return calculateGroupPrice();
        } else {
            return calculateNonGroupPrice();
        }
    }

    private boolean isGroup() {
        return tickets.size() >= 20;
    }

    private double calculateGroupPrice() {
        var totalGroupExtras = movie.calculateTotalGroupExtras();

        return tickets.size() * (GROUP_PRICE + totalGroupExtras);
    }

    private double calculateNonGroupPrice() {
        var totalExtras = movie.calculateTotalExtras();

        return tickets.stream()
                .mapToDouble(ticket -> ticket.calculatePrice() + totalExtras)
                .sum();
    }

}
