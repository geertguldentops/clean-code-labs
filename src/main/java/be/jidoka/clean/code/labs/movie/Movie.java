package be.jidoka.clean.code.labs.movie;

import be.jidoka.clean.code.labs.movie.extra.BalconySeatingAddition;
import be.jidoka.clean.code.labs.movie.extra.Extra;
import be.jidoka.clean.code.labs.movie.extra.OverLengthAddition;
import be.jidoka.clean.code.labs.movie.extra.SpecialMovieDayDiscount;
import be.jidoka.clean.code.labs.movie.extra.ThreeDAddition;
import be.jidoka.clean.code.labs.movie.extra.WeekendAddition;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

public class Movie {

    private final List<Extra> extras = new ArrayList<>();

    public Movie(int runtime, DayOfWeek dayOfWeek, boolean loge, boolean threeD) {
        this.extras.add(new OverLengthAddition(runtime));
        this.extras.add(new SpecialMovieDayDiscount(dayOfWeek));
        this.extras.add(new WeekendAddition(dayOfWeek));
        this.extras.add(new BalconySeatingAddition(loge));
        this.extras.add(new ThreeDAddition(threeD));
    }

    public double calculateTotalGroupExtras() {
        return this.extras.stream()
                .filter(Extra::appliesToGroups)
                .mapToDouble(Extra::getPrice)
                .sum();
    }

    public double calculateTotalExtras() {
        return this.extras.stream()
                .mapToDouble(Extra::getPrice)
                .sum();
    }

}
