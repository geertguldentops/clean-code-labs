package be.jidoka.clean.code.labs.movie;

import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;

import static org.assertj.core.api.Assertions.assertThat;

class BillTest {

    @Test
    void purchaseGeneralAdmission() {
        Bill bill = new BillImpl();
        bill.startPurchase(100, DayOfWeek.MONDAY, false, false);
        bill.addTicket(20, false);

        double purchase = bill.finishPurchase();
        assertThat(purchase).isEqualTo(11.00);
    }

    @Test
    void purchaseMultipleGeneralAdmissions() {
        Bill bill = new BillImpl();
        bill.startPurchase(100, DayOfWeek.MONDAY, false, false);
        bill.addTicket(20, false);
        bill.addTicket(20, false);

        double purchase = bill.finishPurchase();
        assertThat(purchase).isEqualTo(22.00);
    }

    @Test
    void purchaseGroupGeneralAdmissions() {
        Bill bill = new BillImpl();
        bill.startPurchase(100, DayOfWeek.MONDAY, false, false);

        for (int i = 0; i < 20; i++) {
            bill.addTicket(20, false);
        }

        double purchase = bill.finishPurchase();
        assertThat(purchase).isEqualTo(120.00);
    }

    @Test
    void purchaseAsStudent() {
        Bill bill = new BillImpl();
        bill.startPurchase(100, DayOfWeek.MONDAY, false, false);
        bill.addTicket(20, true);

        double purchase = bill.finishPurchase();
        assertThat(purchase).isEqualTo(8.00);
    }

    @Test
    void purchaseAsChild() {
        Bill bill = new BillImpl();
        bill.startPurchase(100, DayOfWeek.MONDAY, false, false);
        bill.addTicket(10, false);

        double purchase = bill.finishPurchase();
        assertThat(purchase).isEqualTo(5.50);
    }

    @Test
    void purchaseAsSenior() {
        Bill bill = new BillImpl();
        bill.startPurchase(100, DayOfWeek.MONDAY, false, false);
        bill.addTicket(66, false);

        double purchase = bill.finishPurchase();
        assertThat(purchase).isEqualTo(6.00);
    }

    // TODO: What if child and student?!

    // TODO: Why interface?
    // TODO: Why so many parameters in method?

}