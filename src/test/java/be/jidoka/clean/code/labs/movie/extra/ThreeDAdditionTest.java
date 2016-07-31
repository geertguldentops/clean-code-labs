package be.jidoka.clean.code.labs.movie.extra;

import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class ThreeDAdditionTest {

    @Test
    public void shouldAlwaysReturnTrue_onAppliesToGroups() {
        assertThat(new ThreeDAddition(true).appliesToGroups()).isTrue();
        assertThat(new ThreeDAddition(false).appliesToGroups()).isTrue();
    }

    @Test
    public void shouldReturn3_onGetPrice_with3D() {
        assertThat(new ThreeDAddition(true).getPrice()).isEqualTo(3.0);
    }

    @Test
    public void shouldReturn0_onGetPrice_withNot3D() {
        assertThat(new ThreeDAddition(false).getPrice()).isZero();
    }

}