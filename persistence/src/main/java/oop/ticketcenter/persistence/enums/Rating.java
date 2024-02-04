package oop.ticketcenter.persistence.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Rating {
    ONE_STAR(1),
    ONE_STAR_AND_HALF(1.5),
    TWO_STARS(2),
    TWO_STARS_AND_HALF(2.5),
    THREE_STARS(3),
    THREE_STARS_AND_HALF(3.5),
    FOUR_STARS(4),
    FOUR_STARS_AND_HALF(4.5),
    FIVE_STARS(5);

    private final double value;
    public static Rating getByValue(double value) {
        for (Rating rating : values()) {
            if (rating.getValue() == value) {
                return rating;
            }
        }
        throw new IllegalArgumentException("No rating found with value: " + value);
    }
}
