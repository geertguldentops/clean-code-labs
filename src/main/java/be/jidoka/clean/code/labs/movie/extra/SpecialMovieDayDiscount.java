package be.jidoka.clean.code.labs.movie.extra;

import java.time.DayOfWeek;

import static java.time.DayOfWeek.THURSDAY;

public class SpecialMovieDayDiscount implements Extra {

    private static final double SPECIAL_MOVIE_DAY_DISCOUNT = -2.0;

    private final DayOfWeek dayOfWeek;

    public SpecialMovieDayDiscount(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    @Override
    public boolean appliesToGroups() {
        return false;
    }

    @Override
    public double getPrice() {
        return THURSDAY.equals(dayOfWeek) ? SPECIAL_MOVIE_DAY_DISCOUNT : 0.0;
    }

}
