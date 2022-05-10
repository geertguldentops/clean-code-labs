package be.jidoka.clean.code.labs.movie;

import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;

import static org.assertj.core.api.Assertions.assertThat;

class PurchaseTest {

	private final Bill bill = new BillImpl();

	@Test
	void no_ticket_purchased() {
		bill.startPurchase(119, DayOfWeek.MONDAY, false, false);

		assertThat(bill.finishPurchase()).isEqualTo(0.00);
	}

	@Test
	void one_default_ticket() {
		bill.startPurchase(119, DayOfWeek.MONDAY, false, false);
		bill.addTicket(20, false);

		assertThat(bill.finishPurchase()).isEqualTo(11.00);
	}

	@Test
	void two_default_ticket() {
		bill.startPurchase(119, DayOfWeek.MONDAY, false, false);
		bill.addTicket(20, false);
		bill.addTicket(20, false);

		assertThat(bill.finishPurchase()).isEqualTo(22.00);
	}

	@Test
	void one_default_student_ticket() {
		bill.startPurchase(119, DayOfWeek.MONDAY, false, false);
		bill.addTicket(20, true);

		assertThat(bill.finishPurchase()).isEqualTo(8.00);
	}

	@Test
	void two_default_student_tickets() {
		bill.startPurchase(119, DayOfWeek.MONDAY, false, false);
		bill.addTicket(20, true);
		bill.addTicket(20, true);

		assertThat(bill.finishPurchase()).isEqualTo(16.00);
	}

	@Test
	void over_65_years_old() {
		bill.startPurchase(119, DayOfWeek.MONDAY, false, false);
		bill.addTicket(66, false);

		assertThat(bill.finishPurchase()).isEqualTo(6.00);
	}

	@Test
	void under_13_years_old() {
		bill.startPurchase(119, DayOfWeek.MONDAY, false, false);
		bill.addTicket(12, false);

		assertThat(bill.finishPurchase()).isEqualTo(5.50);
	}

	// Boundaries ages
		// 13
		// 65
	// senior + student same time
	// Switch case gebruiken?

}