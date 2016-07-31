package be.jidoka.clean.code.labs.movie.extra;

import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class BalconySeatingAdditionTest {

    @Test
    public void shouldAlwaysReturnTrue_onAppliesToGroups() {
        assertThat(new BalconySeatingAddition(true).appliesToGroups()).isTrue();
        assertThat(new BalconySeatingAddition(false).appliesToGroups()).isTrue();
    }

    @Test
    public void shouldReturn2_onGetPrice_withLoge() {
        assertThat(new BalconySeatingAddition(true).getPrice()).isEqualTo(2.0);
    }

    @Test
    public void shouldReturn0_onGetPrice_withNotLoge() {
        assertThat(new BalconySeatingAddition(false).getPrice()).isZero();
    }

}