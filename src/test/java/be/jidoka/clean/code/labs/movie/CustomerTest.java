package be.jidoka.clean.code.labs.movie;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.fest.assertions.api.Assertions.assertThat;

public class CustomerTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void shouldCreateCustomer_onCreateCustomer_withValidAgeAndStudentCombination() {
        assertThat(new Customer(17, true)).isNotNull();
    }

    @Test
    public void shouldReturnTrue_onIsChild_withCustomerIsYoungerThan13() {
        assertThat(new Customer(12, false).isChild()).isTrue();
    }

    @Test
    public void shouldReturnFalse_onIsChild_withCustomerIs13() {
        assertThat(new Customer(13, false).isChild()).isFalse();
    }

    @Test
    public void shouldReturnFalse_onIsChild_withCustomerIsOlderThan13() {
        assertThat(new Customer(14, false).isChild()).isFalse();
    }

    @Test
    public void shouldReturnTrue_onIsSeniorCitizen_withCustomerIs65() {
        assertThat(new Customer(65, false).isSeniorCitizen()).isTrue();
    }

    @Test
    public void shouldReturnTrue_onIsSeniorCitizen_withCustomerIsOlderThan65() {
        assertThat(new Customer(72, false).isSeniorCitizen()).isTrue();
    }

    @Test
    public void shouldReturnFalse_onIsSeniorCitizen_withCustomerIsYoungerThan64() {
        assertThat(new Customer(64, false).isSeniorCitizen()).isFalse();
    }

    @Test
    public void shouldReturnTrue_onIsStudent_withCustomerIsStudent() {
        assertThat(new Customer(18, true).isStudent()).isTrue();
    }

    @Test
    public void shouldReturnFalse_onIsStudent_withCustomerIsNotAStudent() {
        assertThat(new Customer(18, false).isStudent()).isFalse();
    }

    // Note that the "requirements" document does not cover what to do in this situation!
    @Test
    public void shouldThrowIllegalArgumentException_onCreateCustomer_withCustomerIsChildAndStudent() {
        thrown.expect(IllegalArgumentException.class);

        new Customer(12, true);
    }

}