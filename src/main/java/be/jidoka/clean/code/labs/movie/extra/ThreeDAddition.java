package be.jidoka.clean.code.labs.movie.extra;

public class ThreeDAddition implements Extra {

    private static final double THREE_D_ADDITION = 3.0;

    private final boolean threeD;

    public ThreeDAddition(boolean threeD) {
        this.threeD = threeD;
    }

    @Override
    public boolean appliesToGroups() {
        return true;
    }

    @Override
    public double getPrice() {
        return threeD ? THREE_D_ADDITION : 0.0;
    }

}
