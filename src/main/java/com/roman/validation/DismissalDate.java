package com.roman.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DismissalDateValidation.class)
public @interface DismissalDate {

    String message() default "Dismissal date must be greater than admission date";

    Class<?>[] groups() default {};

    public abstract Class<? extends Payload>[] payload() default {};
}
