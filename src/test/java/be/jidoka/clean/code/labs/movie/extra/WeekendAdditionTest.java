package be.jidoka.clean.code.labs.movie.extra;

import org.junit.Test;

import static java.time.DayOfWeek.FRIDAY;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.DayOfWeek.THURSDAY;
import static java.time.DayOfWeek.TUESDAY;
import static java.time.DayOfWeek.WEDNESDAY;
import static org.assertj.core.api.Assertions.assertThat;

public class WeekendAdditionTest {

    @Test
    public void shouldAlwaysReturnTrue_onAppliesToGroups() {
        assertThat(new WeekendAddition(MONDAY).appliesToGroups()).isTrue();
        assertThat(new WeekendAddition(TUESDAY).appliesToGroups()).isTrue();
        assertThat(new WeekendAddition(WEDNESDAY).appliesToGroups()).isTrue();
        assertThat(new WeekendAddition(THURSDAY).appliesToGroups()).isTrue();
        assertThat(new WeekendAddition(FRIDAY).appliesToGroups()).isTrue();
        assertThat(new WeekendAddition(SATURDAY).appliesToGroups()).isTrue();
        assertThat(new WeekendAddition(SUNDAY).appliesToGroups()).isTrue();
    }

    @Test
    public void shouldReturnOneAndAHalf_onGetPrice_withDayOfWeekSaturday() {
        assertThat(new WeekendAddition(SATURDAY).getPrice()).isEqualTo(1.5);
    }

    @Test
    public void shouldReturnOneAndAHalf_onGetPrice_withDayOfWeekSunday() {
        assertThat(new WeekendAddition(SUNDAY).getPrice()).isEqualTo(1.5);
    }

    @Test
    public void shouldReturn0_onGetPrice_withDayOfWeekIsWeekDay() {
        assertThat(new WeekendAddition(MONDAY).getPrice()).isZero();
        assertThat(new WeekendAddition(TUESDAY).getPrice()).isZero();
        assertThat(new WeekendAddition(WEDNESDAY).getPrice()).isZero();
        assertThat(new WeekendAddition(THURSDAY).getPrice()).isZero();
        assertThat(new WeekendAddition(FRIDAY).getPrice()).isZero();
    }

}