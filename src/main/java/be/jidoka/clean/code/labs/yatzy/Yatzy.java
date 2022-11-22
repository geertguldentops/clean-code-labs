package be.jidoka.clean.code.labs.yatzy;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.IntStream;

import static java.util.Comparator.reverseOrder;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.*;

public class Yatzy {

    private final int[] dice;

    public Yatzy(int... dice) {
        this.dice = dice;
    }

    public int chance() {
        return Arrays.stream(dice).sum();
    }

    public int yatzy() {
        return uniqueDice().size() == 1 ? 50 : 0;
    }

    public int ones() {
        return sumDiceMatching(1);
    }

    public int twos() {
        return sumDiceMatching(2);
    }

    public int threes() {
        return sumDiceMatching(3);
    }

    public int fours() {
        return sumDiceMatching(4);
    }

    public int fives() {
        return sumDiceMatching(5);
    }

    public int sixes() {
        return sumDiceMatching(6);
    }

    public int onePair() {
        return xOfAKind(2);
    }

    public int twoPairs() {
        List<Integer> pairsFromHighestToLowest = diceOfAKindFromHighestToLowest(2);

        if (pairsFromHighestToLowest.size() == 2) {
            return pairsFromHighestToLowest.get(0) * 2 + pairsFromHighestToLowest.get(1) * 2;
        } else {
            return 0;
        }
    }

    public int threeOfAKind() {
        return xOfAKind(3);
    }

    public int fourOfAKind() {
        return xOfAKind(4);
    }

    public int smallStraight() {
        return straight(set(1, 5));
    }

    public int largeStraight() {
        return straight(set(2, 6));
    }

    public int fullHouse() {
        int onePair = onePair();
        int threeOfAKind = threeOfAKind();

        if (onePair != 0 && threeOfAKind != 0) {
            return onePair + threeOfAKind;
        } else {
            return 0;
        }
    }

    private Set<Integer> uniqueDice() {
        return Arrays.stream(dice)
                .boxed()
                .collect(toSet());
    }

    private int sumDiceMatching(int number) {
        return Arrays.stream(dice)
                .filter(die -> die == number)
                .sum();
    }

    private int xOfAKind(int number) {
        return diceOfAKindFromHighestToLowest(number).stream()
                .findFirst()
                .map(die -> die * number)
                .orElse(0);
    }

    private List<Integer> diceOfAKindFromHighestToLowest(int number) {
        return tally().entrySet().stream()
                .filter(numberToCount -> numberToCount.getValue() >= number)
                .map(Map.Entry::getKey)
                .sorted(reverseOrder())
                .collect(toList());
    }

    private Map<Integer, Long> tally() {
        return IntStream.rangeClosed(1, 6)
                .boxed()
                .collect(toMap(identity(), this::countDiceMatching));
    }

    private long countDiceMatching(int number) {
        return Arrays.stream(dice)
                .filter(die -> die == number)
                .count();
    }

    private int straight(Set<Integer> numbers) {
        Set<Integer> uniqueDice = uniqueDice();

        return uniqueDice.containsAll(numbers) ?
                uniqueDice.stream()
                        .mapToInt(Integer::intValue)
                        .sum() :
                0;
    }

    private Set<Integer> set(int startInclusive, int endInclusive) {
        return IntStream.rangeClosed(startInclusive, endInclusive)
                .boxed()
                .collect(toSet());
    }
}
