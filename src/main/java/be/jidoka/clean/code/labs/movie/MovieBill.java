package be.jidoka.clean.code.labs.movie;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

public class MovieBill implements Bill {

    private List<Ticket> tickets;

    @Override
    public void startPurchase(int runtime, DayOfWeek dayOfWeek, boolean loge, boolean threeD) {
        tickets = new ArrayList<>();
    }

    @Override
    public void addTicket(int age, boolean student) {
        if (student) {
            tickets.add(new StudentTicket());
        } else {
            if (age < 13) {
                tickets.add(new ChildrenTicket());
            } else if (age >= 65) {
                tickets.add(new SeniorCitizenTicket());
            } else {
                tickets.add(new GeneralAdmissionTicket());
            }
        }
    }

    @Override
    public double finishPurchase() {
        return tickets.stream()
                .map(Ticket::getAdmission)
                .mapToDouble(Double::doubleValue)
                .sum();
    }
}