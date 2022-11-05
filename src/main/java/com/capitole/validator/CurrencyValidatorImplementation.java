package com.capitole.validator;

import java.util.Objects;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.capitole.enums.ECurrencyNumCode;

public class CurrencyValidatorImplementation implements ConstraintValidator<CurrencyValidator, Object> {

   private boolean wildcard;

   public void initialize(CurrencyValidator constraintAnnotation) {
      this.wildcard = constraintAnnotation.wildcard();
   }

   @Override
   public boolean isValid(Object submittedValue, ConstraintValidatorContext cvc) {
      if (Objects.isNull(submittedValue)) {
         return true;
      }
      if (wildcard && ("-1".equalsIgnoreCase(String.valueOf(submittedValue)) || ECurrencyNumCode.isValidObject(submittedValue))) {
         return true;
      }
      return ECurrencyNumCode.isValidObject(submittedValue);
   }
}
