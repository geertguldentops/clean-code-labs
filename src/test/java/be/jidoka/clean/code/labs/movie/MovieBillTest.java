package be.jidoka.clean.code.labs.movie;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.DayOfWeek;

import static java.time.DayOfWeek.MONDAY;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.data.Offset.offset;

class MovieBillTest {

	private final MovieBill bill = new MovieBill();

	@Test
	void finishPurchaseWithoutTickets() {
		bill.startPurchase(90, MONDAY, false, false);

		double result = bill.finishPurchase();
		assertThat(result).isCloseTo(0, offset(0.01));
	}

	@Test
	void finishPurchaseWithOneDefaultTicket() {
		bill.startPurchase(90, MONDAY, false, false);
		bill.addTicket(42, false);

		double result = bill.finishPurchase();
		assertThat(result).isCloseTo(11, offset(0.01));
	}

	@ParameterizedTest(name = "{0}")
	@CsvSource({
			"finishPurchaseWith2DefaultTickets, 2, 22.0",
			"finishPurchaseWith19DefaultTickets, 19, 209.0",
			"finishPurchaseWithGroupDefaultTickets, 20, 120.0",
	})
	void finishPurchaseWithMultipleButNotGroupDefaultTickets(String name, int numberOfTickets, double expectedResult) {
		bill.startPurchase(90, MONDAY, false, false);
		for (int i = 0; i < numberOfTickets; i++) {
			bill.addTicket(42, false);
		}

		double result = bill.finishPurchase();
		assertThat(result).isCloseTo(expectedResult, offset(0.01));
	}

	@Disabled
	@Test
	void finishPurchaseWithOneStudentTicket() {
		bill.startPurchase(90, MONDAY, false, false);
		bill.addTicket(42, true);

		double result = bill.finishPurchase();
		assertThat(result).isCloseTo(8, offset(0.01));
	}

	@ParameterizedTest(name = "{0}")
	@CsvSource({
			"finishPurchaseWithOneDefaultTicket, 90, MONDAY, false, false, 42, false, 11",
	})
	void zombies(String name,
	             int runtime,
	             DayOfWeek dayOfWeek,
	             boolean loge,
	             boolean threeD,
	             int age,
	             boolean student,
	             double expectedPrice) {
		bill.startPurchase(runtime, dayOfWeek, loge, threeD);
		bill.addTicket(age, student);

		double result = bill.finishPurchase();
		assertThat(result).isCloseTo(expectedPrice, offset(0.01));
	}

}