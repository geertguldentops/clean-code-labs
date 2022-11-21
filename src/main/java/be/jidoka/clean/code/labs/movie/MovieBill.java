package be.jidoka.clean.code.labs.movie;

import java.time.DayOfWeek;

public class MovieBill implements Bill {

    private double totalPrice;

    @Override
    public void startPurchase(int runtime, DayOfWeek dayOfWeek, boolean loge, boolean threeD) {

    }

    @Override
    public void addTicket(int age, boolean student) {
        if (age >= 65) {
            totalPrice += 6.00;
        } else {
            if (student) {
                totalPrice += 8.00;
            } else {
                totalPrice += 11.00;
            }
        }
    }

    @Override
    public double finishPurchase() {
        return totalPrice;
    }
}
