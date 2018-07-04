package be.jidoka.clean.code.labs.movie.extra;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BalconySeatingAdditionTest {

    @Test
    void shouldAlwaysReturnTrue_onAppliesToGroups() {
        assertThat(new BalconySeatingAddition(true).appliesToGroups()).isTrue();
        assertThat(new BalconySeatingAddition(false).appliesToGroups()).isTrue();
    }

    @Test
    void shouldReturn2_onGetPrice_withLoge() {
        assertThat(new BalconySeatingAddition(true).getPrice()).isEqualTo(2.0);
    }

    @Test
    void shouldReturn0_onGetPrice_withNotLoge() {
        assertThat(new BalconySeatingAddition(false).getPrice()).isZero();
    }

}