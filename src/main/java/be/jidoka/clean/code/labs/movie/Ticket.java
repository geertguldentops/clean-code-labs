package be.jidoka.clean.code.labs.movie;

public class Ticket {

	private final int age;
	private final boolean student;

	public Ticket(int age, boolean student) {
		this.age = age;
		this.student = student;
	}

	public int getAge() {
		return age;
	}

	public boolean isStudent() {
		return student;
	}
}
