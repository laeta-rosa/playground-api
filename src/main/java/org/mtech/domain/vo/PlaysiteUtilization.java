package org.mtech.domain.vo;

import lombok.NonNull;

import java.math.BigDecimal;

import static java.math.RoundingMode.HALF_UP;

public record PlaysiteUtilization(@NonNull BigDecimal percentage) {

    public PlaysiteUtilization {
        if (percentage.signum() < 0) {
            throw new IllegalArgumentException(
                "PlaysiteUtilization '%s' cannot be constructed, because percentage must be greater than or equal to 0"
                    .formatted(percentage)
            );
        }
    }

    public static PlaysiteUtilization of(BigDecimal value) {
        return new PlaysiteUtilization(value);
    }

    @Override
    public String toString() {
        return percentage.setScale(2, HALF_UP) + "%";
    }
}
