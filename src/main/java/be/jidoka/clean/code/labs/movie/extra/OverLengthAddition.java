package be.jidoka.clean.code.labs.movie.extra;

public class OverLengthAddition implements Extra {

    private static final double OVER_LENGTH_ADDITION = 1.5;

    private final int runtime;

    public OverLengthAddition(int runtime) {
        this.runtime = runtime;
    }

    @Override
    public boolean appliesToGroups() {
        return true;
    }

    @Override
    public double getPrice() {
        return runtime > 120 ? OVER_LENGTH_ADDITION : 0.0;
    }

}
