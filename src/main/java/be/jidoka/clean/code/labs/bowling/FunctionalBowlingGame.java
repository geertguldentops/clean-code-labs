package be.jidoka.clean.code.labs.bowling;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

class FunctionalBowlingGame {

    private static final int MAX_NUMBER_OF_FRAMES_IN_SINGLE_GAME = 10;

    private FunctionalBowlingGame() {
        // No need to instantiate
    }

    public static int calculateScore(List<Integer> rolls) {
        return frames(rolls).stream()
                .limit(MAX_NUMBER_OF_FRAMES_IN_SINGLE_GAME)
                .flatMap(Collection::stream)
                .mapToInt(Integer::intValue)
                .sum();
    }

    public static List<List<Integer>> frames(List<Integer> rolls) {
        if (rolls.isEmpty()) {
            return new ArrayList<>();
        } else {
            var partitions = new ArrayList<List<Integer>>();

            if (isStrike(rolls)) {
                partitions.add(take(rolls, 3));
                partitions.addAll(frames(drop(rolls, 1)));
            } else if (isSpare(rolls)) {
                partitions.add(take(rolls, 3));
                partitions.addAll(frames(drop(rolls, 2)));
            } else {
                partitions.add(take(rolls, 2));
                partitions.addAll(frames(drop(rolls, 2)));
            }

            return partitions;
        }
    }

    private static List<Integer> take(List<Integer> rolls, int n) {
        var subset = new ArrayList<Integer>();

        for (int i = 0; i < n; i++) {
            if (i < rolls.size()) {
                subset.add(rolls.get(i));
            }
        }

        return subset;
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

}
