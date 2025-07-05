package org.mtech.adapter.inbound.rest.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@RequiredArgsConstructor
public class AttractionValidator implements ConstraintValidator<ValidAttraction, String> {

    @AllArgsConstructor
    enum AttractionName {
        SWINGS("double swings"),
        CAROUSEL("carousel"),
        SLIDE("slide"),
        BALL_PIT("ball pit");

        private final String name;

        public static boolean isValidName(String name) {
            return Arrays.stream(values())
                    .anyMatch(value -> value.name.equalsIgnoreCase(name)
            );
        }

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) return true;

        return AttractionName.isValidName(value);
    }

}
