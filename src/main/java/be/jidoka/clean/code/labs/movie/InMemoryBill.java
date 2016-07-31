package be.jidoka.clean.code.labs.movie;

import be.jidoka.clean.code.labs.movie.extra.BalconySeatingAddition;
import be.jidoka.clean.code.labs.movie.extra.Extra;
import be.jidoka.clean.code.labs.movie.extra.OverLengthAddition;
import be.jidoka.clean.code.labs.movie.extra.SpecialMovieDayDiscount;
import be.jidoka.clean.code.labs.movie.extra.ThreeDAddition;
import be.jidoka.clean.code.labs.movie.extra.WeekendAddition;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

public class InMemoryBill implements Bill {

    private static final double GROUP_PRICE = 6.0;

    private List<Ticket> tickets = new ArrayList<>();
    private List<Extra> extras = new ArrayList<>();

    @Override
    public void startPurchase(int runtime, DayOfWeek dayOfWeek, boolean loge, boolean threeD) {
        this.extras.add(new OverLengthAddition(runtime));
        this.extras.add(new SpecialMovieDayDiscount(dayOfWeek));
        this.extras.add(new WeekendAddition(dayOfWeek));
        this.extras.add(new BalconySeatingAddition(loge));
        this.extras.add(new ThreeDAddition(threeD));
    }

    @Override
    public void addTicket(int age, boolean student) {
        tickets.add(new Ticket(age, student));
    }

    @Override
    public double finishPurchase() {
        if (isGroup()) {
            final double sumOfAllGroupExtras = extras.stream()
                    .filter(Extra::appliesToGroups)
                    .mapToDouble(Extra::getPrice)
                    .sum();

            return tickets.size() * (GROUP_PRICE + sumOfAllGroupExtras);
        } else {
            final double sumOfAllExtras = extras.stream()
                    .mapToDouble(Extra::getPrice)
                    .sum();

            return tickets.stream()
                    .mapToDouble(ticket -> ticket.calculatePrice() + sumOfAllExtras)
                    .sum();
        }
    }

    private boolean isGroup() {
        return tickets.size() >= 20;
    }

}
