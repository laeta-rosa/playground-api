package org.mtech.adapter.inbound.rest.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = AttractionValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidAttraction {
    String message() default "Attraction should be one of allowed attractions: Double Swings, Carousel, Slide, Ball Pit";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
