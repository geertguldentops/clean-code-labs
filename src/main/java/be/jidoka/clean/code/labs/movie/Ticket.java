package be.jidoka.clean.code.labs.movie;

public interface Ticket {

    double getAdmission();

    default double getGroupAdmission() {
        return 6.0;
    };
}
