package com.capitole.validator.utils;

import java.util.Optional;

import com.capitole.exceptions.CapitoleException;
import com.capitole.exceptions.CapitoleExceptionCode;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ValidationUtils {

   public void validateNotEmpty(Optional optional, CapitoleExceptionCode exceptionCode, Object... values) throws CapitoleException {
      if (optional.isEmpty()) {
         throw new CapitoleException(exceptionCode, values);
      }
   }

   public void validateEmpty(Optional optional, CapitoleExceptionCode exceptionCode, Object... values) throws CapitoleException {
      if (!optional.isEmpty()) {
         throw new CapitoleException(exceptionCode, values);
      }
   }

}
