package com.capitole.exceptions;

import lombok.Getter;

@Getter
public class CapitoleException extends Exception {

   protected final CapitoleExceptionCode exceptionCode;

   protected final transient Object[] messageParams;

   public CapitoleException(CapitoleExceptionCode exceptionCode, Object... messageParams) {
      this.exceptionCode = exceptionCode;
      this.messageParams = messageParams;
   }

   public CapitoleException(Exception cause, CapitoleExceptionCode exceptionCode, Object... messageParams) {
      super(cause);
      this.exceptionCode = exceptionCode;
      this.messageParams = messageParams;
   }

   @Override
   public String getMessage() {
      return this.exceptionCode.getLocaleMessage(messageParams);
   }

}