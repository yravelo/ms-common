package com.capitole.exceptions;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;

import com.capitole.enums.IEnumResource;

import lombok.extern.log4j.Log4j2;

@Log4j2
public enum CapitoleExceptionCode implements IEnumResource {

   GENERIC_ERROR(HttpStatus.PRECONDITION_FAILED),
   PARAM_FORMAT_NOT_VALID(HttpStatus.PRECONDITION_FAILED),
   PARAM_TYPE_NOT_VALID(HttpStatus.PRECONDITION_FAILED),
   UNKNOWN_PRODUCT(HttpStatus.PRECONDITION_FAILED),
   PRODUCT_ALREADY_EXISTS_WITH_SAME_CODE(HttpStatus.PRECONDITION_FAILED);

   private static final String PROPERTIES_NAME = "com/capitole/exceptions/exception";

   private final HttpStatus httpStatus;

   private final Level level;

   CapitoleExceptionCode(HttpStatus status) {
      this.httpStatus = status;
      this.level = Level.ERROR;
   }

   CapitoleExceptionCode(HttpStatus status, Level level) {
      this.httpStatus = status;
      this.level = level;
   }

   public HttpStatus getHttpStatus() {
      return httpStatus;
   }

   public Level getLevel() {
      return level;
   }

   public int getHttpStatusCode() {
      return this.httpStatus.value();
   }

   @Override
   public String getEnumKey() {
      return StringUtils.EMPTY;
   }

   @Override
   public String getResourceKey() {
      return this.name();
   }

   @Override
   public Logger getLog() {
      return log;
   }

   @Override
   public String getPropertiesName() {
      return PROPERTIES_NAME;
   }

}