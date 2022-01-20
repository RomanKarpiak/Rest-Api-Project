package com.roman.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = HeadOfDepartmentValidation.class)
public @interface HeadOfDepartment {

    String message() default "A department can have only one head";

    Class<?>[] groups() default {};

    public abstract Class<? extends Payload>[] payload() default {};
}
