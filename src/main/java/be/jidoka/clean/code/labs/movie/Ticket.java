package be.jidoka.clean.code.labs.movie;

public class Ticket {

    private static final double GENERAL_ADMISSION = 11.0;
    private static final double STUDENT_ADMISSION = 8.0;

    private final boolean student;

    public Ticket(boolean student) {
        this.student = student;
    }

    public double calculatePrice() {
        if (student) {
            return STUDENT_ADMISSION;
        } else {
            return GENERAL_ADMISSION;
        }
    }
}
