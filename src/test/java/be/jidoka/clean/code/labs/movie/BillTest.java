package be.jidoka.clean.code.labs.movie;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;

import static org.assertj.core.api.Assertions.assertThat;

public class BillTest {

    private Bill bill;

    @BeforeEach
    public void setUp() {
        bill = new TicketOffice();
    }

    @Test
    public void singleTicketGeneralAdmissionShouldBe11WithoutExceptions() {
        // Given
        bill.startPurchase(70, DayOfWeek.MONDAY, false, false);
        bill.addTicket(29, false);

        // When
        double price = bill.finishPurchase();

        // Then
        assertThat(price).isEqualTo(11.0);
    }

    @Test
    public void twoTicketsGeneralAdmissionShouldBe22WithoutExceptions() {
        // Given
        bill.startPurchase(70, DayOfWeek.MONDAY, false, false);
        bill.addTicket(29, false);
        bill.addTicket(29, false);

        // When
        double price = bill.finishPurchase();

        // Then
        assertThat(price).isEqualTo(22.0);
    }

    @Test
    public void studentsShouldPay8DollarAdmission() {
        // Given
        bill.startPurchase(70, DayOfWeek.MONDAY, false, false);
        bill.addTicket(29, true);

        // When
        double price = bill.finishPurchase();

        // Then
        assertThat(price).isEqualTo(8.0);
    }

    // What if we want to purchase for multiple movies? --> Out of scope
    // TODO: New test: Student and senior then senior price applies
}
