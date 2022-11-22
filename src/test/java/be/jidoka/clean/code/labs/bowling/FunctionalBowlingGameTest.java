package be.jidoka.clean.code.labs.bowling;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static be.jidoka.clean.code.labs.bowling.FunctionalBowlingGame.calculateScore;
import static be.jidoka.clean.code.labs.bowling.FunctionalBowlingGame.frames;
import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.range;
import static org.assertj.core.api.Assertions.assertThat;

class FunctionalBowlingGameTest {

    @Nested
    class Scoring {

        @Test
        void all_gutter_rolls() {
            assertThat(calculateScore(range(0, 20).map(operand -> 0).boxed().collect(toList()))).isZero();
        }

        @Test
        void all_normal_rolls() {
            assertThat(calculateScore(range(0, 20).map(operand -> 3).boxed().collect(toList()))).isEqualTo(60);
        }

        @Test
        void one_spare() {
            assertThat(calculateScore(List.of(5, 5, 2, 1))).isEqualTo(15);
        }

        @Test
        void one_strike() {
            assertThat(calculateScore(List.of(10, 3, 2, 1, 0))).isEqualTo(21);
        }

        @Test
        void perfect_game() {
            assertThat(calculateScore(range(0, 12).map(operand -> 10).boxed().collect(toList()))).isEqualTo(300);
        }

    }

    @Nested
    class FramePartitioning {

        @Test
        void partitions_no_frames() {
            assertThat(frames(Collections.emptyList())).isEmpty();
        }

        @Test
        void partitions_normal_frames() {
            assertThat(frames(List.of(0, 0, 1, 1))).isEqualTo(List.of(List.of(0, 0), List.of(1, 1)));
        }

        @Test
        void partitions_spare_frames() {
            assertThat(frames(List.of(5, 5, 2, 1))).isEqualTo(List.of(List.of(5, 5, 2), List.of(2, 1)));
        }

        @Test
        void partitions_strike_frames() {
            assertThat(frames(List.of(10, 3, 2, 1, 0))).isEqualTo(List.of(List.of(10, 3, 2), List.of(3, 2), List.of(1, 0)));
        }

        @Test
        void partitions_all_strike_frames() {
            assertThat(frames(range(0, 12).map(operand -> 10).boxed().collect(toList())))
                    .isEqualTo(
                            List.of(
                                    List.of(10, 10, 10),
                                    List.of(10, 10, 10),
                                    List.of(10, 10, 10),
                                    List.of(10, 10, 10),
                                    List.of(10, 10, 10),
                                    List.of(10, 10, 10),
                                    List.of(10, 10, 10),
                                    List.of(10, 10, 10),
                                    List.of(10, 10, 10),
                                    List.of(10, 10, 10),
                                    List.of(10, 10),
                                    List.of(10)
                            )
                    );
        }

    }

}