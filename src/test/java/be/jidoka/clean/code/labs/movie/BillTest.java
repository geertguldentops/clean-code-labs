package be.jidoka.clean.code.labs.movie;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;

import static org.assertj.core.api.Assertions.assertThat;

class BillTest {

	private Bill bill;

	@BeforeEach
	public void setUp() {
		bill = new Calculator();
	}

	@Test
	public void generalAdmissionShouldEqualDefaultPrice() {
		// Arrange
		bill.startPurchase(100, DayOfWeek.WEDNESDAY, false, false);
		bill.addTicket(18, false);

		// Act
		double ticketPrice = bill.finishPurchase();

		// Assert
		assertThat(ticketPrice).isEqualTo(11.0);
	}

	@Test
	public void studentAdmissionShouldEqualStudentPrice() {
		bill.startPurchase(100, DayOfWeek.WEDNESDAY, false, false);
		bill.addTicket(18, true);

		double ticketPrice = bill.finishPurchase();

		assertThat(ticketPrice).isEqualTo(8.0);
	}

	@Test
	public void multipleGeneralAdmissionsShouldEqualDefaultPrice() {
		// Arrange
		bill.startPurchase(100, DayOfWeek.WEDNESDAY, false, false);
		bill.addTicket(18, false);
		bill.addTicket(18, false);

		// Act
		double ticketPrice = bill.finishPurchase();

		// Assert
		assertThat(ticketPrice).isEqualTo(22.0);
	}

	@Test
	public void _3DShouldAddToTicketPrice() {
		bill.startPurchase(100, DayOfWeek.WEDNESDAY, false, true);
		bill.addTicket(18, false);

		double ticketPrice = bill.finishPurchase();

		assertThat(ticketPrice).isEqualTo(14.0);
	}

	// ZOMBIE

	// Zero
	// One /
	// Many /

	// Boundaries
	// Interface /
	// Exception

}
