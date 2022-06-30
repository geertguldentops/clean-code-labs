package be.jidoka.clean.code.labs.movie.purchase.ticket;

import be.jidoka.clean.code.labs.movie.purchase.extra.Extra;

import java.util.Set;

public abstract class Ticket {

	public static Ticket create(int age, boolean student) {
		if (age >= 65) {
			return new SeniorTicket();
		} else if (age < 13) {
			return new ChildTicket();
		} else {
			if (student) {
				return new StudentTicket();
			} else {
				return new GeneralAdmissionTicket();
			}
		}
	}

	public static Ticket groupTicket() {
		return new GroupTicket();
	}

	public final double calculateTotalPrice(Set<Extra> extras) {
		return basePrice() + calculateExtras(extras);
	}

	protected abstract double basePrice();

	protected double calculateExtras(Set<Extra> extras) {
		return extras.stream()
				.filter(extra -> extra.appliesTo(this))
				.mapToDouble(Extra::price)
				.sum();
	}

}
