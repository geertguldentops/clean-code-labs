package be.jidoka.clean.code.labs.movie;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

public class MovieBill implements Bill {

    private List<Ticket> tickets = new ArrayList<>();

    @Override
    public void startPurchase(int runtime, DayOfWeek dayOfWeek, boolean loge, boolean threeD) {

    }

    @Override
    public void addTicket(int age, boolean student) {
        Ticket ticket = new Ticket(age, student);
        tickets.add(ticket);
    }

    @Override
    public double finishPurchase() {
        return tickets.stream()
                .map(Ticket::getPrice)
                .mapToDouble(Double::doubleValue)
                .sum();
    }

    private record Ticket(int age, boolean student) {

        public double getPrice() {
            if (student) {
                return 8.00;
            } else {
                if (age >= 65) {
                    return 6.00;
                } else {
                    return 11.00;
                }
            }
        }

    }

}
