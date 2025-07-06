package org.mtech.infrastructure.adapter.inbound.rest.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.mtech.domain.vo.AttractionType;

@RequiredArgsConstructor
public class AttractionValidator implements ConstraintValidator<ValidAttraction, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) return true;
        return AttractionType.isValidName(value);
    }

}
