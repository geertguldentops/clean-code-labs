package be.jidoka.clean.code.labs.movie;

import be.jidoka.clean.code.labs.movie.purchase.Purchase;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.DayOfWeek;

import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.THURSDAY;
import static java.util.stream.IntStream.rangeClosed;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.data.Offset.offset;
import static org.junit.jupiter.params.provider.EnumSource.Mode.INCLUDE;

class BillTest {

    private final Bill bill = new Purchase();

    @Nested
    class DegenerateCases {

        @Test
        void purchase_without_tickets() {
            bill.startPurchase(100, MONDAY, false, false);

            assertThat(bill.finishPurchase()).isZero();
        }

    }

    @Nested
    class Individuals {

        @Test
        void purchase_student_ticket() {
            bill.startPurchase(100, MONDAY, false, false);

            bill.addTicket(33, true);

            assertThat(bill.finishPurchase()).isCloseTo(8.00, offset(0.001));
        }

        @ValueSource(ints = {13, 14, 35, 63, 64})
        @ParameterizedTest
        void purchase_general_admission_ticket(int age) {
            bill.startPurchase(100, MONDAY, false, false);

            bill.addTicket(age, false);

            assertThat(bill.finishPurchase()).isCloseTo(11.00, offset(0.001));
        }

        @ValueSource(ints = {65, 66})
        @ParameterizedTest
        void purchase_senior_ticket(int age) {
            bill.startPurchase(100, MONDAY, false, false);

            bill.addTicket(age, false);

            assertThat(bill.finishPurchase()).isCloseTo(6.00, offset(0.001));
        }

        @ValueSource(ints = {11, 12})
        @ParameterizedTest
        void purchase_child_ticket(int age) {
            bill.startPurchase(100, MONDAY, false, false);

            bill.addTicket(age, false);

            assertThat(bill.finishPurchase()).isCloseTo(5.50, offset(0.001));
        }

        @Test
        void purchase_senior_and_student_ticket() {
            bill.startPurchase(100, MONDAY, false, false);

            bill.addTicket(65, true);

            assertThat(bill.finishPurchase()).isCloseTo(6.00, offset(0.001));
        }

        @Test
        void purchase_child_and_student_ticket() {
            bill.startPurchase(100, MONDAY, false, false);

            bill.addTicket(12, true);

            assertThat(bill.finishPurchase()).isCloseTo(5.50, offset(0.001));
        }

    }

    @Nested
    class Group {

        @CsvSource(value = {
                "2  | 22.00",
                "19 | 209.00"
        }, delimiter = '|')
        @ParameterizedTest
        void purchase_multiple_tickets_but_not_group(int numberOfPeople, double expectedResult) {
            bill.startPurchase(100, MONDAY, false, false);

            rangeClosed(1, numberOfPeople).forEach(value -> bill.addTicket(33, false));

            assertThat(bill.finishPurchase()).isCloseTo(expectedResult, offset(0.001));
        }

        @CsvSource(value = {
                "20 | 120.00",
                "21 | 126.00"
        }, delimiter = '|')
        @ParameterizedTest
        void purchase_multiple_tickets_as_a_group(int numberOfPeople, double expectedResult) {
            bill.startPurchase(100, MONDAY, false, false);

            rangeClosed(1, numberOfPeople).forEach(value -> bill.addTicket(33, false));

            assertThat(bill.finishPurchase()).isCloseTo(expectedResult, offset(0.001));
        }

    }

    @Nested
    class Exceptions {

        @Test
        void purchase_single_ticket_for_3D_movie() {
            bill.startPurchase(100, MONDAY, false, true);

            bill.addTicket(33, false);

            assertThat(bill.finishPurchase()).isCloseTo(11.00 + 3.00, offset(0.001));
        }

        @Test
        void purchase_multiple_tickets_but_not_group_for_3D_movie() {
            bill.startPurchase(100, MONDAY, false, true);

            bill.addTicket(33, false);
            bill.addTicket(33, false);

            assertThat(bill.finishPurchase()).isCloseTo(((11.00 + 3.00) * 2), offset(0.001));
        }

        @Test
        void purchase_multiple_tickets_as_a_group_for_3D_movie() {
            bill.startPurchase(100, MONDAY, false, true);

            rangeClosed(1, 20).forEach(value -> bill.addTicket(33, false));

            assertThat(bill.finishPurchase()).isCloseTo(((6.00 + 3.00) * 20), offset(0.001));
        }

        @Test
        void purchase_single_ticket_for_over_length_movie() {
            bill.startPurchase(121, MONDAY, false, false);

            bill.addTicket(33, false);

            assertThat(bill.finishPurchase()).isCloseTo(11.00 + 1.50, offset(0.001));
        }

        @Test
        void purchase_single_ticket_for_over_length_and_3D_movie() {
            bill.startPurchase(121, MONDAY, false, true);

            bill.addTicket(33, false);

            assertThat(bill.finishPurchase()).isCloseTo(11.00 + 3.00 + 1.50, offset(0.001));
        }

        @Test
        void purchase_single_ticket_on_special_movie_day() {
            bill.startPurchase(100, THURSDAY, false, false);

            bill.addTicket(33, false);

            assertThat(bill.finishPurchase()).isCloseTo(11.00 - 2.00, offset(0.001));
        }

        @Test
        void purchase_multiple_tickets_as_a_group_on_special_movie_day() {
            bill.startPurchase(100, THURSDAY, false, false);

            rangeClosed(1, 20).forEach(value -> bill.addTicket(33, false));

            assertThat(bill.finishPurchase()).isCloseTo((6.00 * 20), offset(0.001));
        }

        @EnumSource(value = DayOfWeek.class, mode = INCLUDE, names = {"SATURDAY", "SUNDAY"})
        @ParameterizedTest
        void purchase_single_ticket_on_weekend(DayOfWeek weekendDay) {
            bill.startPurchase(100, weekendDay, false, false);

            bill.addTicket(33, false);

            assertThat(bill.finishPurchase()).isCloseTo(11.00 + 1.50, offset(0.001));
        }

        @EnumSource(value = DayOfWeek.class, mode = INCLUDE, names = {"SATURDAY", "SUNDAY"})
        @ParameterizedTest
        void purchase_multiple_tickets_as_a_group_on_weekend(DayOfWeek weekendDay) {
            bill.startPurchase(100, weekendDay, false, false);

            rangeClosed(1, 20).forEach(value -> bill.addTicket(33, false));

            assertThat(bill.finishPurchase()).isCloseTo(((6.00 + 1.5) * 20), offset(0.001));
        }

        @Test
        void purchase_single_ticket_for_balcony() {
            bill.startPurchase(100, MONDAY, true, false);

            bill.addTicket(33, false);

            assertThat(bill.finishPurchase()).isCloseTo(11.00 + 2.00, offset(0.001));
        }

        @Test
        void purchase_multiple_tickets_but_not_group_for_balcony() {
            bill.startPurchase(100, MONDAY, true, false);

            rangeClosed(1, 20).forEach(value -> bill.addTicket(33, false));

            assertThat(bill.finishPurchase()).isCloseTo(((6.00 + 2.0) * 20), offset(0.001));
        }

    }

}