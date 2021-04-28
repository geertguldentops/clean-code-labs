package be.jidoka.clean.code.labs.movie;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

public class MovieBill implements Bill {

    private List<Ticket> tickets;
    private boolean threeD;
    private double surCharge;


    @Override
    public void startPurchase(int runtime, DayOfWeek dayOfWeek, boolean loge, boolean threeD) {
        this.threeD = threeD;
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

        if (this.threeD) {
            surCharge += 3.0;
        }
    }

    @Override
    public double finishPurchase() {
        double sum;

        if (tickets.size() >= 20) {
            sum = tickets.stream()
                    .mapToDouble(Ticket::getGroupAdmission)
                    .sum();
        }
        else {
            sum = tickets.stream()
                    .mapToDouble(Ticket::getAdmission)
                    .sum();
        }

        return sum + surCharge;
    }
}