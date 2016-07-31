package be.jidoka.clean.code.labs.movie.extra;

import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class OverLengthAdditionTest {

    @Test
    public void shouldAlwaysReturnTrue_onAppliesToGroups() {
        assertThat(new OverLengthAddition(112).appliesToGroups()).isTrue();
        assertThat(new OverLengthAddition(139).appliesToGroups()).isTrue();
    }

    @Test
    public void shouldReturnOneAndAHalf_onGetPrice_withRunTimeMoreThan120() {
        assertThat(new OverLengthAddition(121).getPrice()).isEqualTo(1.5);
    }

    @Test
    public void shouldReturn0_onGetPrice_withRunTimeIs120() {
        assertThat(new OverLengthAddition(120).getPrice()).isZero();
    }

    @Test
    public void shouldReturn0_onGetPrice_withRunTimeLessThan120() {
        assertThat(new OverLengthAddition(119).getPrice()).isZero();
    }


}