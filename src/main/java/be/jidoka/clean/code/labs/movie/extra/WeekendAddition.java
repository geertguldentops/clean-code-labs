package be.jidoka.clean.code.labs.movie.extra;

import java.time.DayOfWeek;
import java.util.EnumSet;

import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;

public class WeekendAddition implements Extra {

    private static final double WEEKEND_ADDITION = 1.5;

    private final DayOfWeek dayOfWeek;

    public WeekendAddition(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    @Override
    public boolean appliesToGroups() {
        return true;
    }

    @Override
    public double getPrice() {
        return EnumSet.of(SATURDAY, SUNDAY).contains(dayOfWeek) ? WEEKEND_ADDITION : 0.0;
    }

}
