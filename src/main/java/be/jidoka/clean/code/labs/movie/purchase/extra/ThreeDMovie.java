package be.jidoka.clean.code.labs.movie.purchase.extra;

import be.jidoka.clean.code.labs.movie.purchase.ticket.Ticket;

public class ThreeDMovie implements Extra {

    private final boolean threeD;

    public ThreeDMovie(boolean threeD) {
        this.threeD = threeD;
    }

    @Override
    public boolean appliesTo(Ticket ticket) {
        return true;
    }

    @Override
    public double price() {
        return threeD ? 3.00 : 0.00;
    }

}
