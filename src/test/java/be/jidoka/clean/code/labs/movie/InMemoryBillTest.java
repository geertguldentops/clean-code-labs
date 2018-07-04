package be.jidoka.clean.code.labs.movie;

import org.junit.jupiter.api.Test;

import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.THURSDAY;
import static java.time.DayOfWeek.TUESDAY;
import static org.assertj.core.api.Assertions.assertThat;

class InMemoryBillTest {

    private final Bill bill = new InMemoryBill();

    @Test
    void shouldReturnGeneralAdmissionPrice_onFinishPurchase_withAddSingleGeneralAdmissionTicket() {
        bill.startPurchase(112, MONDAY, false, false);

        bill.addTicket(27, false);

        final double finalPrice = bill.finishPurchase();

        assertThat(finalPrice).isEqualTo(11.0);
    }

    @Test
    void shouldReturnGeneralAdmissionPriceMultipliedByAmountOfTickets_onFinishPurchase_withAddMultipleGeneralAdmissionTickets() {
        bill.startPurchase(112, MONDAY, false, false);

        bill.addTicket(27, false);
        bill.addTicket(28, false);

        final double finalPrice = bill.finishPurchase();

        assertThat(finalPrice).isEqualTo(22.0);
    }

    @Test
    void shouldReturnStudentPrice_onFinishPurchase_withAddSingleStudentTicket() {
        bill.startPurchase(112, MONDAY, false, false);

        bill.addTicket(21, true);

        final double finalPrice = bill.finishPurchase();

        assertThat(finalPrice).isEqualTo(8.0);
    }

    @Test
    void shouldReturnSeniorCitizenPrice_onFinishPurchase_withAddSingleSeniorCitizenTicket() {
        bill.startPurchase(112, MONDAY, false, false);

        bill.addTicket(65, false);

        final double finalPrice = bill.finishPurchase();

        assertThat(finalPrice).isEqualTo(6.0);
    }

    @Test
    void shouldReturnChildrenPrice_onFinishPurchase_withAddSingleChildrenTicket() {
        bill.startPurchase(112, MONDAY, false, false);

        bill.addTicket(12, false);

        final double finalPrice = bill.finishPurchase();

        assertThat(finalPrice).isEqualTo(5.5);
    }

    @Test
    void shouldReturnGroupPrice_onFinishPurchase_withTwentyOrMoreTickets() {
        bill.startPurchase(112, MONDAY, false, false);

        for (int i = 0; i < 20; i++) {
            bill.addTicket(25, false);
        }

        final double finalPrice = bill.finishPurchase();

        assertThat(finalPrice).isEqualTo(120.0);
    }

    @Test
    void shouldReturnGeneralAdmissionPricePlus3DMovieAddition_onFinishPurchase_withAddSingleGeneralAdmissionTicketAnd3DMovie() {
        bill.startPurchase(112, MONDAY, false, true);

        bill.addTicket(27, false);

        final double finalPrice = bill.finishPurchase();

        assertThat(finalPrice).isEqualTo(14.0);
    }

    @Test
    void shouldReturnGeneralAdmissionPricePlusOverLengthAddition_onFinishPurchase_withAddSingleGeneralAdmissionTicketAndLongMovie() {
        bill.startPurchase(121, MONDAY, false, false);

        bill.addTicket(27, false);

        final double finalPrice = bill.finishPurchase();

        assertThat(finalPrice).isEqualTo(12.5);
    }

    @Test
    void shouldReturnGeneralAdmissionPriceMinusSpecialMovieDayDiscount_onFinishPurchase_withAddSingleGeneralAdmissionTicketAndSpecialMovieDay() {
        bill.startPurchase(119, THURSDAY, false, false);

        bill.addTicket(27, false);

        final double finalPrice = bill.finishPurchase();

        assertThat(finalPrice).isEqualTo(9.0);
    }

    // Special movie day does NOT apply to groups!
    @Test
    void shouldReturnGroupPrice_onFinishPurchase_withTwentyOrMoreTicketsAndSpecialMovieDay() {
        bill.startPurchase(112, THURSDAY, false, false);

        for (int i = 0; i < 20; i++) {
            bill.addTicket(25, false);
        }

        final double finalPrice = bill.finishPurchase();

        assertThat(finalPrice).isEqualTo(120.0);
    }

    @Test
    void shouldReturnGeneralAdmissionPricePlusWeekendAddition_onFinishPurchase_withAddSingleGeneralAdmissionTicketAndWeekendDay() {
        bill.startPurchase(119, SATURDAY, false, false);

        bill.addTicket(27, false);

        final double finalPrice = bill.finishPurchase();

        assertThat(finalPrice).isEqualTo(12.5);
    }

    @Test
    void shouldReturnGeneralAdmissionPricePlusBalconySeatingAddition_onFinishPurchase_withAddSingleGeneralAdmissionTicketAndBalconySeating() {
        bill.startPurchase(119, TUESDAY, true, false);

        bill.addTicket(27, false);

        final double finalPrice = bill.finishPurchase();

        assertThat(finalPrice).isEqualTo(13);
    }

    @Test
    void shouldReturnCorrectTotalPrice_onFinishPurchase_withMixAllKindsOfAdditions() {
        bill.startPurchase(121, SATURDAY, true, true);

        bill.addTicket(27, false);

        final double finalPrice = bill.finishPurchase();

        assertThat(finalPrice).isEqualTo(19.0);
    }

    @Test
    void shouldReturnCorrectTotalPrice_onFinishPurchase_withMixAllKindsOfTickets() {
        bill.startPurchase(112, MONDAY, false, false);

        bill.addTicket(21, true);
        bill.addTicket(25, false);
        bill.addTicket(19, true);
        bill.addTicket(23, false);
        bill.addTicket(65, false);
        bill.addTicket(12, false);

        final double finalPrice = bill.finishPurchase();

        assertThat(finalPrice).isEqualTo(49.5);
    }

    @Test
    void shouldReturnCorrectTotalPrice_onFinishPurchase_withMixTicketsAndAdditionsButNotGroup() {
        bill.startPurchase(125, THURSDAY, false, true);

        bill.addTicket(21, true);
        bill.addTicket(25, false);
        bill.addTicket(19, true);
        bill.addTicket(23, false);
        bill.addTicket(65, false);
        bill.addTicket(12, false);

        final double finalPrice = bill.finishPurchase();

        assertThat(finalPrice).isEqualTo(64.5);
    }

    @Test
    void shouldReturnCorrectTotalPrice_onFinishPurchase_withTwentyOrMoreTicketsAndAllAdditions() {
        bill.startPurchase(130, SATURDAY, true, true);

        for (int i = 0; i < 20; i++) {
            bill.addTicket(25, false);
        }

        final double finalPrice = bill.finishPurchase();

        assertThat(finalPrice).isEqualTo(280.0);
    }

}