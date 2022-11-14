package be.jidoka.clean.code.labs.movie;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.DayOfWeek;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.data.Offset.offset;

class BillTest {

	@Test
	void purchase_without_tickets() {
		MovieBill movieBill = new MovieBill();

		movieBill.startPurchase(90, DayOfWeek.MONDAY, false, false);
		// Explicitly do NOT add a ticket!
		double actualAdmissionFee = movieBill.finishPurchase();

		assertThat(actualAdmissionFee).isCloseTo(0.00, offset(0.001));
	}

	@ParameterizedTest
	@ValueSource(ints = { 13, 18, 64 })
	void purchase_single_general_admission_ticket(int age) {
		MovieBill movieBill = new MovieBill();

		movieBill.startPurchase(90, DayOfWeek.MONDAY, false, false);
		movieBill.addTicket(age, false);
		double actualAdmissionFee = movieBill.finishPurchase();

		assertThat(actualAdmissionFee).isCloseTo(11.00, offset(0.001));
	}

	@ParameterizedTest
	@ValueSource(ints = { 13, 18, 64 })
	void purchase_multiple_general_admission_tickets(int age) {
		MovieBill movieBill = new MovieBill();

		movieBill.startPurchase(90, DayOfWeek.MONDAY, false, false);
		movieBill.addTicket(age, false);
		movieBill.addTicket(age, false);
		double actualAdmissionFee = movieBill.finishPurchase();

		assertThat(actualAdmissionFee).isCloseTo(22.00, offset(0.001));
	}

	@ParameterizedTest
	@ValueSource(ints = { 65, 66, 100 })
	void purchase_single_senior_citizen_ticket(int age) {
		MovieBill movieBill = new MovieBill();

		movieBill.startPurchase(90, DayOfWeek.MONDAY, false, false);
		movieBill.addTicket(age, false);
		double actualAdmissionFee = movieBill.finishPurchase();

		assertThat(actualAdmissionFee).isCloseTo(6.00, offset(0.001));
	}

	@ParameterizedTest
	@ValueSource(ints = { 11, 12 })
	void purchase_single_child_ticket(int age) {
		MovieBill movieBill = new MovieBill();

		movieBill.startPurchase(90, DayOfWeek.MONDAY, false, false);
		movieBill.addTicket(age, false);
		double actualAdmissionFee = movieBill.finishPurchase();

		assertThat(actualAdmissionFee).isCloseTo(5.50, offset(0.001));
	}

	@Test
	void purchase_single_student_ticket() {
		MovieBill movieBill = new MovieBill();

		movieBill.startPurchase(90, DayOfWeek.MONDAY, false, false);
		movieBill.addTicket(18, true);
		double actualAdmissionFee = movieBill.finishPurchase();

		assertThat(actualAdmissionFee).isCloseTo(8.00, offset(0.001));
	}

	// TODO: student + child?
	// TODO: student + senior?
	// TODO: test finishPurchase without start --> exception
	// TODO: More than 20 tickets. --> group pricing

}