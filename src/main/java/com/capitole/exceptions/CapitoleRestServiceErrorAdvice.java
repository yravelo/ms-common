package com.capitole.exceptions;

import java.util.Optional;

import static org.springframework.core.Ordered.HIGHEST_PRECEDENCE;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.PRECONDITION_FAILED;

import static com.capitole.exceptions.CapitoleExceptionCode.PARAM_FORMAT_NOT_VALID;
import static com.capitole.exceptions.CapitoleExceptionCode.PARAM_TYPE_NOT_VALID;

import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import lombok.extern.log4j.Log4j2;

@Log4j2
@ControllerAdvice
@Order(HIGHEST_PRECEDENCE)
public class CapitoleRestServiceErrorAdvice {

   private static final String GENERIC_MESSAGE = "Internal Error";

   private static final String VALIDATION_MESSAGE = "ParametersValidator Error";

   private final ExceptionLoggerService exceptionLoggerService;

   public CapitoleRestServiceErrorAdvice(ExceptionLoggerService exceptionLoggerService) {
      this.exceptionLoggerService = exceptionLoggerService;
   }

   @ExceptionHandler({ RuntimeException.class })
   public ResponseEntity<ExceptionResponse> handleException(RuntimeException e) {
      exceptionLoggerService.logException(log, e);
      if (e instanceof MethodArgumentTypeMismatchException) {
         return ResponseEntity
               .status(PRECONDITION_FAILED)
               .body(ExceptionResponse.builder().httpCode(PRECONDITION_FAILED.value()).readableMsg(PARAM_TYPE_NOT_VALID.getLocaleMessage()).build());
      }
      if (e instanceof HttpMessageNotReadableException) {
         return ResponseEntity
               .status(PRECONDITION_FAILED)
               .body(ExceptionResponse
                     .builder()
                     .httpCode(PRECONDITION_FAILED.value())
                     .readableMsg(PARAM_FORMAT_NOT_VALID.getLocaleMessage())
                     .build());
      }

      return ResponseEntity
            .status(INTERNAL_SERVER_ERROR)
            .body(ExceptionResponse.builder().httpCode(INTERNAL_SERVER_ERROR.value()).readableMsg(GENERIC_MESSAGE).build());
   }

   @ExceptionHandler({ CapitoleException.class })
   public ResponseEntity<ExceptionResponse> handleException(CapitoleException e) {
      exceptionLoggerService.logException(log, e);
      return ResponseEntity
            .status(e.getExceptionCode().getHttpStatus())
            .body(ExceptionResponse.builder().httpCode(e.getExceptionCode().getHttpStatusCode()).readableMsg(e.getMessage()).build());
   }

   @ExceptionHandler({ MethodArgumentNotValidException.class })
   public ResponseEntity<ExceptionResponse> handleException(MethodArgumentNotValidException e) {
      exceptionLoggerService.logException(log, e);
      return ResponseEntity
            .status(PRECONDITION_FAILED)
            .body(ExceptionResponse
                  .builder()
                  .httpCode(PRECONDITION_FAILED.value())
                  .readableMsg(getHumanReadableMessage(e))
                  .localizedMessage(e.getLocalizedMessage())
                  .build());
   }

   private String getHumanReadableMessage(MethodArgumentNotValidException e) {
      return Optional
            .of(e.getBindingResult())
            .map(Errors::getFieldError)
            .map(fieldError -> fieldError.getField() + " " + fieldError.getDefaultMessage())
            .orElse(VALIDATION_MESSAGE);
   }
}