package be.jidoka.clean.code.labs.movie;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CustomerTest {

    @Test
    void shouldCreateCustomer_onCreateCustomer_withValidAgeAndStudentCombination() {
        assertThat(new Customer(17, true)).isNotNull();
    }

    @Test
    void shouldReturnTrue_onIsChild_withCustomerIsYoungerThan13() {
        assertThat(new Customer(12, false).isChild()).isTrue();
    }

    @Test
    void shouldReturnFalse_onIsChild_withCustomerIs13() {
        assertThat(new Customer(13, false).isChild()).isFalse();
    }

    @Test
    void shouldReturnFalse_onIsChild_withCustomerIsOlderThan13() {
        assertThat(new Customer(14, false).isChild()).isFalse();
    }

    @Test
    void shouldReturnTrue_onIsSeniorCitizen_withCustomerIs65() {
        assertThat(new Customer(65, false).isSeniorCitizen()).isTrue();
    }

    @Test
    void shouldReturnTrue_onIsSeniorCitizen_withCustomerIsOlderThan65() {
        assertThat(new Customer(72, false).isSeniorCitizen()).isTrue();
    }

    @Test
    void shouldReturnFalse_onIsSeniorCitizen_withCustomerIsYoungerThan64() {
        assertThat(new Customer(64, false).isSeniorCitizen()).isFalse();
    }

    @Test
    void shouldReturnTrue_onIsStudent_withCustomerIsStudent() {
        assertThat(new Customer(18, true).isStudent()).isTrue();
    }

    @Test
    void shouldReturnFalse_onIsStudent_withCustomerIsNotAStudent() {
        assertThat(new Customer(18, false).isStudent()).isFalse();
    }

    // Note that the "requirements" document does not cover what to do in this situation!
    @Test
    void shouldThrowIllegalArgumentException_onCreateCustomer_withCustomerIsChildAndStudent() {
        assertThatThrownBy(() -> new Customer(12, true)).isInstanceOf(IllegalArgumentException.class);
    }

}