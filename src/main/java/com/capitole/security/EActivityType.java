package com.capitole.security;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.Logger;

import com.capitole.enums.IEnumResource;

import lombok.extern.log4j.Log4j2;

@Log4j2
public enum EActivityType implements IEnumResource {

   CREATE(0),
   UPDATE(1),
   REMOVE(2),
   QUERY(3),
   UNKNOWN(4);

   private static final String RESOURCE_PREFIX = StringUtils.upperCase(EActivityType.class.getSimpleName()) + ".";

   private final int code;

   EActivityType(int code) {
      this.code = code;
   }

   public int getCode() {
      return code;
   }

   @Override
   public String getEnumKey() {
      return this.name();
   }

   @Override
   public String getResourceKey() {
      return RESOURCE_PREFIX + this.name();
   }

   @Override
   public Logger getLog() {
      return log;
   }

   public static EActivityType getByValue(int value) {
      for (EActivityType prop : values()) {
         if (prop.getCode() == value) {
            return prop;
         }
      }
      return null;
   }
}
