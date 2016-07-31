package be.jidoka.clean.code.labs.movie;

// TODO: Implement equals() and hashCode() if used in Map or Set.
public class Ticket {

    private static final double GENERAL_ADMISSION_PRICE = 11.0;
    private static final double STUDENT_PRICE = 8.0;
    private static final double SENIOR_CITIZEN_PRICE = 6.0;
    private static final double CHILDREN_PRICE = 5.5;

    private final int age;
    private final boolean student;

    public Ticket(int age, boolean student) {
        if (isChild(age) && student) {
            throw new IllegalArgumentException("A ticket can not be for a student and for a child at the same time!");
        }

        this.age = age;
        this.student = student;
    }

    private static boolean isChild(int age) {
        return age < 13;
    }

    public double calculatePrice() {
        if (isSeniorCitizen(age)) {
            return SENIOR_CITIZEN_PRICE;
        } else if (isChild(age)) {
            return CHILDREN_PRICE;
        }

        if (student) {
            return STUDENT_PRICE;
        } else {
            return GENERAL_ADMISSION_PRICE;
        }
    }

    private static boolean isSeniorCitizen(int age) {
        return age >= 65;
    }

}
