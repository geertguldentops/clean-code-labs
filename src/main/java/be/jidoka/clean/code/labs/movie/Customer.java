package be.jidoka.clean.code.labs.movie;

public class Customer {

    private final int age;
    private final boolean student;

    public Customer(int age, boolean student) {
        this.age = age;
        this.student = student;

        if (isChild() && student) {
            throw new IllegalArgumentException("A customer can not be a child and a student at the same time!");
        }
    }

    public boolean isChild() {
        return age < 13;
    }

    public boolean isSeniorCitizen() {
        return age >= 65;
    }

    public boolean isStudent() {
        return student;
    }

}
