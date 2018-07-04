package be.jidoka.clean.code.labs.movie;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TicketTest {

    @Test
    void shouldReturnGeneralAdmissionPrice_onCalculatePrice_withNormalAgeAndNonStudent() {
        assertThat(new Ticket(27, false).calculatePrice()).isEqualTo(11.0);
    }

    @Test
    void shouldReturnStudentPrice_onCalculatePrice_withNormalAgeAndStudent() {
        assertThat(new Ticket(19, true).calculatePrice()).isEqualTo(8.0);
    }

    @Test
    void shouldReturnSeniorCitizenPrice_onCalculatePrice_withAgeIsSeniorCitizenAndNotStudent() {
        assertThat(new Ticket(65, false).calculatePrice()).isEqualTo(6.0);
    }

    // Note that the "requirements" document does not cover what to do in this situation!
    @Test
    void shouldReturnSeniorCitizenPrice_onCalculatePrice_withAgeIsSeniorCitizenAndStudent() {
        // Be customer-friendly and return the lowest price, which in this situation is the student price.
        assertThat(new Ticket(65, true).calculatePrice()).isEqualTo(6.0);
    }

    @Test
    void shouldReturnChildrenPrice_onCalculatePrice_withAgeIsChildAndNotStudent() {
        assertThat(new Ticket(12, false).calculatePrice()).isEqualTo(5.5);
    }

}