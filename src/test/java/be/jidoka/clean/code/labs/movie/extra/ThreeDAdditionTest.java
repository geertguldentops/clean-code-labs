package be.jidoka.clean.code.labs.movie.extra;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ThreeDAdditionTest {

    @Test
    void shouldAlwaysReturnTrue_onAppliesToGroups() {
        assertThat(new ThreeDAddition(true).appliesToGroups()).isTrue();
        assertThat(new ThreeDAddition(false).appliesToGroups()).isTrue();
    }

    @Test
    void shouldReturn3_onGetPrice_with3D() {
        assertThat(new ThreeDAddition(true).getPrice()).isEqualTo(3.0);
    }

    @Test
    void shouldReturn0_onGetPrice_withNot3D() {
        assertThat(new ThreeDAddition(false).getPrice()).isZero();
    }

}