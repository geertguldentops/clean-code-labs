package be.jidoka.clean.code.labs.movie;

import java.time.DayOfWeek;

public class BillImpl implements Bill {

    private static final double GENERAL_ADMISSION_PRICE = 11.00;
    private static final double STUDENT_PRICE = 8.0;
    private static final double CHILD_PRICE = 5.5;

    private double totalCost = 0.0;

    @Override
    public void startPurchase(int runtime, DayOfWeek dayOfWeek, boolean loge, boolean threeD) {

    }

    @Override
    public void addTicket(int age, boolean student) {
        if (student) {
            totalCost += STUDENT_PRICE;
        } else if (age < 13) {
            totalCost += CHILD_PRICE;
        } else if (age >= 65) {
            totalCost += 6.00;
        } else {
            totalCost += GENERAL_ADMISSION_PRICE;
        }
    }

    @Override
    public double finishPurchase() {
        return totalCost;
    }

}
