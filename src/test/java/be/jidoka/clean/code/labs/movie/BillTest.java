package be.jidoka.clean.code.labs.movie;

import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;

import static org.assertj.core.api.Assertions.assertThat;

class BillTest {

//    @Test
//    void canNotFinishPurchaseWithoutStarting() {
//        Bill bill = new MovieBill();
//
//        ThrowingCallable operation = () -> bill.finishPurchase();
//
//        assertThatThrownBy(operation)
//                .isInstanceOf(IllegalStateException.class);
//    }

    @Test
    void oneGeneralAdmissionTicket() {
        Bill bill = setUpDefaultMovieBill();

        bill.addTicket(30, false);

        assertThat(bill.finishPurchase()).isEqualTo(11.00);
    }

    @Test
    void multipleGeneralAdmissionTickets() {
        Bill bill = setUpDefaultMovieBill();

        bill.addTicket(30, false);
        bill.addTicket(30, false);

        assertThat(bill.finishPurchase()).isEqualTo(22.00);
    }

    @Test
    void oneStudentAdmissionTicket() {
        Bill bill = setUpDefaultMovieBill();

        bill.addTicket(30, true);

        assertThat(bill.finishPurchase()).isEqualTo(8.00);
    }

    @Test
    void oneSeniorCitizenAdmissionTicket() {
        Bill bill = setUpDefaultMovieBill();

        bill.addTicket(65, false);

        assertThat(bill.finishPurchase()).isEqualTo(6.00);
    }

    // TODO: age 64 and age 66
    // TODO: senior + student?

    private static Bill setUpDefaultMovieBill() {
        Bill bill = new MovieBill();
        bill.startPurchase(100, DayOfWeek.MONDAY, false, false);

        return bill;
    }

    // TODO: Multiple admissions together in 1 purchase
    // TODO: can_not_finish_purchase_without_adding_ticket
    // TODO: test input parameter validity (optional vs mandatory)

}