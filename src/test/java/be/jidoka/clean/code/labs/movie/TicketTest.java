package be.jidoka.clean.code.labs.movie;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.fest.assertions.api.Assertions.assertThat;

public class TicketTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void shouldReturnGeneralAdmissionPrice_onCalculatePrice_withNormalAgeAndNonStudent() {
        assertThat(new Ticket(27, false).calculatePrice()).isEqualTo(11.0);
    }

    @Test
    public void shouldReturnStudentPrice_onCalculatePrice_withNormalAgeAndStudent() {
        assertThat(new Ticket(19, true).calculatePrice()).isEqualTo(8.0);
    }

    @Test
    public void shouldReturnSeniorCitizenPrice_onCalculatePrice_withAgeIsSeniorCitizenAndNotStudent() {
        assertThat(new Ticket(65, false).calculatePrice()).isEqualTo(6.0);
    }

    // Note that the "requirements" document does not cover what to do in this situation!
    @Test
    public void shouldReturnSeniorCitizenPrice_onCalculatePrice_withAgeIsSeniorCitizenAndStudent() {
        // Be customer-friendly and return the lowest price, which in this situation is the student price.
        assertThat(new Ticket(65, true).calculatePrice()).isEqualTo(6.0);
    }

    @Test
    public void shouldReturnChildrenPrice_onCalculatePrice_withAgeIsChildAndNotStudent() {
        assertThat(new Ticket(12, false).calculatePrice()).isEqualTo(5.5);
    }

    // Note that the "requirements" document does not cover what to do in this situation!
    @Test
    public void shouldThrowIllegalArgumentException_onCreateTicketWithChildAgeAndIsStudent() {
        thrown.expect(IllegalArgumentException.class);

        new Ticket(12, true);
    }

}