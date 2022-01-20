package com.roman.validation;

import com.roman.dto.EmployeeDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class DismissalDateValidation implements ConstraintValidator<DismissalDate, EmployeeDto> {
    @Override
    public void initialize(DismissalDate constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(EmployeeDto employee, ConstraintValidatorContext constraintValidatorContext) {
        LocalDate admissionDate = employee.getDateOfAdmission();
        LocalDate dismissalDate = employee.getDateOfDismissal();
        if (dismissalDate == null) {
            return true;
        }
        return dismissalDate.isAfter(admissionDate)
                && (dismissalDate.isBefore(LocalDate.now()) || dismissalDate.equals(LocalDate.now()));
    }
}
