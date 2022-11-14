package be.jidoka.clean.code.labs.bowling;

import java.util.ArrayList;
import java.util.List;

class BowlingGame {

	private final List<Integer> rolls = new ArrayList<>();

	public void roll(int pins) {
		rolls.add(pins);
	}

	public int calculateTotalScore() {
		int totalScore = 0;

		int roll = 0;
		for (int frameIndex = 1; frameIndex <= 10; frameIndex++) {
			if (isStrike(roll)) {
				totalScore += 10 + rolls.get(roll + 1) + rolls.get(roll + 2);
				roll += 1;
			} else if (isSpare(roll)) {
				totalScore += 10 + rolls.get(roll + 2);
				roll += 2;
			} else {
				totalScore += rolls.get(roll) + rolls.get(roll + 1);
				roll += 2;
			}
		}

		return totalScore;
	}

	private boolean isStrike(int i) {
		return rolls.get(i) == 10;
	}

	private boolean isSpare(int i) {
		return rolls.get(i) + rolls.get(i + 1) == 10;
	}

}
