package be.jidoka.clean.code.labs.movie;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

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

    @ParameterizedTest
    @ValueSource(ints = {18, 30, 64})
    void oneGeneralAdmissionTicket(int age) {
        Bill bill = setUpDefaultMovieBill();

        bill.addTicket(age, false);

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

    @ParameterizedTest
    @ValueSource(ints = {65, 66, Integer.MAX_VALUE})
    void oneSeniorCitizenAdmissionTicket(int age) {
        Bill bill = setUpDefaultMovieBill();

        bill.addTicket(age, false);

        assertThat(bill.finishPurchase()).isEqualTo(6.00);
    }

    @Test
    void oneStudentAndSeniorCitizenTicket() {
        Bill bill = setUpDefaultMovieBill();

        bill.addTicket(65, true);

        assertThat(bill.finishPurchase()).isEqualTo(8.00);
    }

    private static Bill setUpDefaultMovieBill() {
        Bill bill = new MovieBill();
        bill.startPurchase(100, DayOfWeek.MONDAY, false, false);

        return bill;
    }

    // TODO: Multiple admissions together in 1 purchase
    // TODO: can_not_finish_purchase_without_adding_ticket
    // TODO: test input parameter validity (optional vs mandatory)

}