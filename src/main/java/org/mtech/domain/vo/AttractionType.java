package org.mtech.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum AttractionType {

    SWINGS("double swings", 2),
    CAROUSEL("carousel", 6),
    SLIDE("slide", 1),
    BALL_PIT("ball pit", 5);

    private final String name;
    private final int capacity;

    public static boolean isValidName(String name) {
        return Arrays.stream(values())
                .anyMatch(value -> value.name.equalsIgnoreCase(name)
                );
    }

    public static int getCapacity(String name) {
        return Arrays.stream(values())
                .filter(a -> a.name.equalsIgnoreCase(name))
                .map(AttractionType::getCapacity)
                .findFirst()
                .orElse(0);
    }

}
