package be.jidoka.clean.code.labs.movie;

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