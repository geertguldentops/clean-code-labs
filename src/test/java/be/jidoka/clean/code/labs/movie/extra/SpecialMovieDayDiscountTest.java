package be.jidoka.clean.code.labs.movie.extra;

import org.junit.jupiter.api.Test;

import static java.time.DayOfWeek.FRIDAY;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.DayOfWeek.THURSDAY;
import static java.time.DayOfWeek.TUESDAY;
import static java.time.DayOfWeek.WEDNESDAY;
import static org.assertj.core.api.Assertions.assertThat;

class SpecialMovieDayDiscountTest {

    // It's easier to test all permutations now!
    @Test
    void shouldAlwaysReturnFalse_onAppliesToGroups() {
        assertThat(new SpecialMovieDayDiscount(MONDAY).appliesToGroups()).isFalse();
        assertThat(new SpecialMovieDayDiscount(TUESDAY).appliesToGroups()).isFalse();
        assertThat(new SpecialMovieDayDiscount(WEDNESDAY).appliesToGroups()).isFalse();
        assertThat(new SpecialMovieDayDiscount(THURSDAY).appliesToGroups()).isFalse();
        assertThat(new SpecialMovieDayDiscount(FRIDAY).appliesToGroups()).isFalse();
        assertThat(new SpecialMovieDayDiscount(SATURDAY).appliesToGroups()).isFalse();
        assertThat(new SpecialMovieDayDiscount(SUNDAY).appliesToGroups()).isFalse();
    }

    @Test
    void shouldReturnMinus2_onGetPrice_withDayOfWeekThursday() {
        assertThat(new SpecialMovieDayDiscount(THURSDAY).getPrice()).isEqualTo(-2.0);
    }

    // It's easier to test all permutations now!
    @Test
    void shouldReturn0_onGetPrice_withDayOfWeekOtherThanThursday() {
        assertThat(new SpecialMovieDayDiscount(MONDAY).getPrice()).isZero();
        assertThat(new SpecialMovieDayDiscount(TUESDAY).getPrice()).isZero();
        assertThat(new SpecialMovieDayDiscount(WEDNESDAY).getPrice()).isZero();
        assertThat(new SpecialMovieDayDiscount(FRIDAY).getPrice()).isZero();
        assertThat(new SpecialMovieDayDiscount(SATURDAY).getPrice()).isZero();
        assertThat(new SpecialMovieDayDiscount(SUNDAY).getPrice()).isZero();
    }

}