package com.capitole.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER })
@Constraint(validatedBy = CurrencyValidatorImplementation.class)
public @interface CurrencyValidator {

   String message() default "{com.capitole.messages.currencyNumValidator}";

   boolean wildcard() default false;

   Class<?>[] groups() default {};

   Class<? extends Payload>[] payload() default {};

}
