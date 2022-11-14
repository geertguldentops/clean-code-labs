package be.jidoka.clean.code.labs.bowling;

import org.junit.jupiter.api.Test;

import static java.util.stream.IntStream.range;
import static org.assertj.core.api.Assertions.assertThat;

class BowlingGameTest {

	private final BowlingGame bowlingGame = new BowlingGame();

	@Test
	void all_gutter_rolls() {
		range(0, 20).forEach(i -> bowlingGame.roll(0));

		assertThat(bowlingGame.calculateTotalScore()).isZero();
	}

	@Test
	void all_normal_rolls() {
		range(0, 20).forEach(i -> bowlingGame.roll(3));

		assertThat(bowlingGame.calculateTotalScore()).isEqualTo(60);
	}

	@Test
	void one_spare_roll() {
		rollSpare();
		bowlingGame.roll(3);
		range(0, 17).forEach(i -> bowlingGame.roll(0));

		assertThat(bowlingGame.calculateTotalScore()).isEqualTo(16);
	}

	@Test
	void one_strike_roll() {
		rollStrike();
		bowlingGame.roll(3);
		bowlingGame.roll(4);
		range(0, 16).forEach(i -> bowlingGame.roll(0));

		assertThat(bowlingGame.calculateTotalScore()).isEqualTo(24);
	}

	@Test
	void perfect_game() {
		range(0, 12).forEach(i -> rollStrike());

		assertThat(bowlingGame.calculateTotalScore()).isEqualTo(300);
	}

	private void rollSpare() {
		bowlingGame.roll(2);
		bowlingGame.roll(8);
	}

	private void rollStrike() {
		bowlingGame.roll(10);
	}

}