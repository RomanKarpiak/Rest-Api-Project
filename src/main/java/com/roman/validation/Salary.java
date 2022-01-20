package com.roman.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = SalaryValidation.class)
public @interface Salary {

    String message() default "The salary of an employee may not exceed " +
            "the salary of the head and " +
            "the salary of the head cannot be less than the salary of the employee";

    Class<?>[] groups() default {};

    public abstract Class<? extends Payload>[] payload() default {};
}
