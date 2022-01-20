package com.roman.validation;

import com.roman.dto.EmployeeDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class AdmissionDateValidation implements ConstraintValidator<AdmissionDate, EmployeeDto> {

    @Override

    public boolean isValid(EmployeeDto employee, ConstraintValidatorContext constraintValidatorContext) {
        LocalDate admissionDate = employee.getDateOfAdmission();
        LocalDate birthday = employee.getBirthday();
        return admissionDate.isAfter(birthday)
                && (admissionDate.isBefore(LocalDate.now()) || admissionDate.equals(LocalDate.now()));
    }

    @Override
    public void initialize(AdmissionDate constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

}
