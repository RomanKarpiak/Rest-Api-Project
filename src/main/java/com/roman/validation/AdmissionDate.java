package com.roman.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AdmissionDateValidation.class)
public @interface AdmissionDate {

    String message() default "Admission date must be more than date of birthday";

    Class<?>[] groups() default {};

    public abstract Class<? extends Payload>[] payload() default {};

}
