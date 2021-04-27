package be.jidoka.clean.code.labs.movie;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;

import static org.assertj.core.api.Assertions.assertThat;

class BillTest {

    private Bill bill;

    // Arrange
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

}