package com.capitole.exceptions;

import static java.util.Objects.isNull;
import static java.util.Optional.ofNullable;

import static org.apache.commons.lang3.exception.ExceptionUtils.getRootCause;
import static org.apache.commons.lang3.exception.ExceptionUtils.getRootCauseMessage;
import static org.apache.logging.log4j.Level.ERROR;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;
import org.javatuples.Pair;
import org.springframework.stereotype.Component;

import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
public class ExceptionLoggerService {

   public void logException(Logger logger, Throwable throwable) {
      Pair<Level, String> msgLevel = getMessageWithLevel(throwable);
      logger.log(msgLevel.getValue0(), msgLevel.getValue1(), ofNullable(getRootCause(throwable)).map(rootCause -> throwable).orElse(throwable));
   }

   private Pair<Level, String> getMessageWithLevel(Throwable throwable) {
      if (isNull(throwable)) {
         return new Pair<>(ERROR, "Generic error information");
      }
      if (throwable instanceof CapitoleException) {
         Level level = ((CapitoleException) throwable).exceptionCode.getLevel();
         return new Pair<>(level, throwable.getMessage());
      } else {
         return new Pair<>(ERROR, getRootCauseMessage(throwable));
      }
   }

}
