package com.roman.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class GenderEnumValidatorImpl implements ConstraintValidator<EnumValidator, Enum> {

    private List<String> valueList = null;

    @Override
    public void initialize(EnumValidator constraintAnnotation) {
        valueList = new ArrayList<>();
        for (String val : constraintAnnotation.acceptedValues()) {
            valueList.add(val.toLowerCase());
        }
    }

    @Override
    public boolean isValid(Enum value, ConstraintValidatorContext context) {
        return valueList.contains(value.toString().toLowerCase());
    }
}
