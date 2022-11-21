package be.jidoka.clean.code.labs.movie;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

public class MovieBill implements Bill {

    private static final int MINIMAL_GROUP_SIZE = 20;

    private List<Ticket> tickets = new ArrayList<>();
    private Movie movie;

    @Override
    public void startPurchase(int runtime, DayOfWeek dayOfWeek, boolean loge, boolean threeD) {
        movie = new Movie(runtime, threeD);
    }

    @Override
    public void addTicket(int age, boolean student) {
        Ticket ticket = new Ticket(age, student);
        ticket.addSurplus(movie.getSurplus());
        tickets.add(ticket);
    }

    @Override
    public double finishPurchase() {
        if (tickets.size() >= MINIMAL_GROUP_SIZE) {
            return tickets.size() * Ticket.GROUP_PRICE;
        }

        return tickets.stream()
                .map(Ticket::getPrice)
                .mapToDouble(Double::doubleValue)
                .sum();
    }

    private static class Ticket {

        public static final double GROUP_PRICE = 6.00;

        private final int age;
        private final boolean student;
        private double price;

        private Ticket(int age, boolean student) {
            this.age = age;
            this.student = student;
            this.price = calculatePrice();
        }

        public double getPrice() {
            return price;
        }

        private double calculatePrice() {
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

        public void addSurplus(double surplus) {
            this.price += surplus;
        }

    }

    private record Movie(int runtime, boolean threeD) {

        public double getSurplus() {
            if (threeD) {
                return 3.00;
            } else {
                return 0.00;
            }
        }

    }

}
