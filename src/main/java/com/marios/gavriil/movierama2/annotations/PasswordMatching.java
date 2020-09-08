package com.marios.gavriil.movierama2.annotations;

import com.marios.gavriil.movierama2.validators.PasswordMatchingValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = PasswordMatchingValidator.class)
@Documented
public @interface PasswordMatching {

    String message() default "No matching Passwords";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
