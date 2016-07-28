package be.jidoka.clean.code.labs.movie;

import java.time.DayOfWeek;

public interface Bill {

    /**
     * (1) New customers arrive at your ticket booth and tell you what movie they'd like to see (so keep it in mind!)
     *
     * @param runtime   movie's runtime in minutes
     * @param dayOfWeek day of the week (enum)
     * @param loge      true if seating category is 'loge')
     * @param threeD    true if the movie's shown in 3D
     */
    void startPurchase(int runtime, DayOfWeek dayOfWeek, boolean loge, boolean threeD);

    /**
     * (2) Add a ticket to the customers' bill
     *
     * @param age     the age of the ticket buyer in years
     * @param student true if the ticket buyer is a student
     */
    void addTicket(int age, boolean student);

    /**
     * (3) Calculate the total admission for the current customer(s)
     *
     * @return total in dollars, TODO: It is better to use BigDecimal but double is simpler to implement.
     */
    double finishPurchase();

}
