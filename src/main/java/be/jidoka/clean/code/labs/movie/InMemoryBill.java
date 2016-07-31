package be.jidoka.clean.code.labs.movie;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.DayOfWeek.THURSDAY;

public class InMemoryBill implements Bill {

    private static final double GROUP_PRICE = 6.0;

    private static final double THREE_D_ADDITION = 3.0;
    private static final double OVER_LENGTH_ADDITION = 1.5;
    private static final double WEEKEND_ADDITION = 1.5;
    private static final double BALCONY_SEATING_ADDITION = 2.0;

    private static final double SPECIAL_MOVIE_DAY_DISCOUNT = -2.0;

    private List<Ticket> tickets = new ArrayList<>();

    private int runtime;
    private DayOfWeek dayOfWeek;
    private boolean threeD;
    private boolean loge;

    @Override
    public void startPurchase(int runtime, DayOfWeek dayOfWeek, boolean loge, boolean threeD) {
        this.runtime = runtime;
        this.dayOfWeek = dayOfWeek;
        this.loge = loge;
        this.threeD = threeD;
    }

    @Override
    public void addTicket(int age, boolean student) {
        tickets.add(new Ticket(age, student));
    }

    @Override
    public double finishPurchase() {
        if (isGroup()) {
            return tickets.size() * (GROUP_PRICE + calculateAdditions());
        } else {
            final double extras = calculateAdditions() + calculateDiscounts();

            return tickets.stream()
                    .mapToDouble(ticket -> ticket.calculatePrice() + extras)
                    .sum();
        }
    }

    private boolean isGroup() {
        return tickets.size() >= 20;
    }

    private double calculateAdditions() {
        double totalAdditions = 0.0;

        totalAdditions += threeD ? THREE_D_ADDITION : 0.0;
        totalAdditions += runtime > 120 ? OVER_LENGTH_ADDITION : 0.0;
        totalAdditions += EnumSet.of(SATURDAY, SUNDAY).contains(dayOfWeek) ? WEEKEND_ADDITION : 0.0;
        totalAdditions += loge ? BALCONY_SEATING_ADDITION : 0.0;

        return totalAdditions;
    }

    private double calculateDiscounts() {
        return THURSDAY.equals(dayOfWeek) ? SPECIAL_MOVIE_DAY_DISCOUNT : 0.0;
    }

}
