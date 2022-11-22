package be.jidoka.clean.code.labs.bowling;

import java.util.ArrayList;
import java.util.List;

class FunctionalBowlingGame {

    private static final int MAX_NUMBER_OF_FRAMES_IN_SINGLE_GAME = 10;

    private FunctionalBowlingGame() {
        // No need to instantiate
    }

    public static int calculateScore(List<Integer> rolls) {
        return partitionIntoFrames(rolls).stream()
                .limit(MAX_NUMBER_OF_FRAMES_IN_SINGLE_GAME)
                .map(Frame::calculateScore)
                .mapToInt(Integer::intValue)
                .sum();
    }

    public static List<Frame> partitionIntoFrames(List<Integer> rolls) {
        if (rolls.isEmpty()) {
            return new ArrayList<>();
        } else {
            var framePartitions = new ArrayList<Frame>();

            if (isStrike(rolls)) {
                framePartitions.add(take(rolls, 3));
                framePartitions.addAll(partitionIntoFrames(drop(rolls, 1)));
            } else if (isSpare(rolls)) {
                framePartitions.add(take(rolls, 3));
                framePartitions.addAll(partitionIntoFrames(drop(rolls, 2)));
            } else {
                framePartitions.add(take(rolls, 2));
                framePartitions.addAll(partitionIntoFrames(drop(rolls, 2)));
            }

            return framePartitions;
        }
    }

    private static Frame take(List<Integer> rolls, int n) {
        var frameRolls = rolls.stream()
                .limit(n)
                .toList();

        return new Frame(frameRolls);
    }

    private static List<Integer> drop(List<Integer> rolls, int n) {
        return rolls.subList(n, rolls.size());
    }

    private static boolean isStrike(List<Integer> rolls) {
        return rolls.get(0) == 10;
    }

    private static boolean isSpare(List<Integer> rolls) {
        return rolls.get(0) + rolls.get(1) == 10;
    }

    public record Frame(List<Integer> rolls) {

        public int calculateScore() {
            return rolls.stream()
                    .mapToInt(Integer::intValue)
                    .sum();
        }

    }

}
