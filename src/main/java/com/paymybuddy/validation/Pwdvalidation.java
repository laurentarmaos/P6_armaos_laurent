package com.paymybuddy.validation;

import static java.lang.annotation.ElementType.FIELD;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Retention(RUNTIME)
@Target(FIELD)
@Constraint(validatedBy = {PwdvalidationValidator.class})
public @interface Pwdvalidation {

	String message() default "Le mot de passe doit contenir au moins une minuscule, une majuscule, un nombre et un caractère spécial";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
