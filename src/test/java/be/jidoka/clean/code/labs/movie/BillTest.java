package be.jidoka.clean.code.labs.movie;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.DayOfWeek;

import static org.assertj.core.api.Assertions.assertThat;

class BillTest {

    private Bill bill;

    // Arrange
    @Nested
    class DefaultPurchaseTest {

        @BeforeEach
        void setUp() {
            bill = new MovieBill();
            bill.startPurchase(60, DayOfWeek.MONDAY, false, false);
        }

        @Test
        void billWithoutTicketsReturnsZero() {
            // Act
            double purchase = bill.finishPurchase();

            // Assert
            assertThat(purchase).isZero();
        }

        @Test
        void billWithTicketReturnsGeneralAdmission() {
            // Act
            bill.addTicket(32, false);
            double purchase = bill.finishPurchase();

            // Assert
            assertThat(purchase).isEqualTo(11.0);
        }

        @Test
        void billWithTicketReturnsStudentAdmission() {
            bill.addTicket(32, true);

            double purchase = bill.finishPurchase();
            assertThat(purchase).isEqualTo(8.0);
        }

        @Test
        void billWithSeniorCitizenAndChildrenTickets() {
            bill.addTicket(8, false);
            bill.addTicket(6, false);
            bill.addTicket(67, false);

            double purchase = bill.finishPurchase();
            assertThat(purchase).isEqualTo(17.0);
        }

        @CsvSource(value = {
                "19     |    209.0",
                "20     |    120.0",
                "21     |    126.0"
        }, delimiterString = "|")
        @ParameterizedTest
        void billWithXPeopleOrMoreReturnsAdmission(int people, double admission) {
            for (int i = 0; i < people; i++) {
                bill.addTicket(35,false);
            }

            double purchase = bill.finishPurchase();
            assertThat(purchase).isEqualTo(admission);
        }

    }

    @Nested
    class threeDMoviePurchaseTest {

        @BeforeEach
        void setUp() {
            bill = new MovieBill();
            bill.startPurchase(60, DayOfWeek.MONDAY, false, true);
        }

        @Test
        void billWithoutTicketsReturnsZero() {
            // Act
            double purchase = bill.finishPurchase();

            // Assert
            assertThat(purchase).isZero();
        }

        @Test
        void billWithTicketReturnsGeneralAdmission() {
            // Act
            bill.addTicket(32, false);
            double purchase = bill.finishPurchase();

            // Assert
            assertThat(purchase).isEqualTo(14.0);
        }
    }

}