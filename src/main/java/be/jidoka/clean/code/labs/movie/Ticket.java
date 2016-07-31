package be.jidoka.clean.code.labs.movie;

// TODO: Implement equals() and hashCode() if used in Map or Set.
public class Ticket {

    private static final double GENERAL_ADMISSION_PRICE = 11.0;
    private static final double STUDENT_PRICE = 8.0;
    private static final double SENIOR_CITIZEN_PRICE = 6.0;
    private static final double CHILDREN_PRICE = 5.5;

    private final Customer customer;

    public Ticket(int age, boolean student) {
        this.customer = new Customer(age, student);
    }

    public double calculatePrice() {
        if (customer.isSeniorCitizen()) {
            return SENIOR_CITIZEN_PRICE;
        } else if (customer.isChild()) {
            return CHILDREN_PRICE;
        } else {
            if (customer.isStudent()) {
                return STUDENT_PRICE;
            } else {
                return GENERAL_ADMISSION_PRICE;
            }
        }
    }

}
