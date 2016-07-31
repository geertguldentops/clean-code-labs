package be.jidoka.clean.code.labs.movie.extra;

public class BalconySeatingAddition implements Extra {

    private static final double BALCONY_SEATING_ADDITION = 2.0;

    private final boolean loge;

    public BalconySeatingAddition(boolean loge) {
        this.loge = loge;
    }

    @Override
    public boolean appliesToGroups() {
        return true;
    }

    @Override
    public double getPrice() {
        return loge ? BALCONY_SEATING_ADDITION : 0.0;
    }

}
