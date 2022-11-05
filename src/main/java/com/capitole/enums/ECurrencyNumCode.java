package com.capitole.enums;

import static org.apache.commons.lang3.StringUtils.upperCase;
import static org.apache.commons.lang3.math.NumberUtils.createInteger;
import static org.apache.commons.lang3.math.NumberUtils.isParsable;

import org.apache.logging.log4j.Logger;

import lombok.experimental.UtilityClass;
import lombok.extern.log4j.Log4j2;

@Log4j2
public enum ECurrencyNumCode implements IEnumResource {

   SPAIN("Spain", "Euro", Constants.EUR, 978, 2),
   UNITED_STATES("United States", "US Dollar", Constants.USD, 840, 2);

   private static final String RESOURCE_PREFIX = upperCase(ECurrencyNumCode.class.getSimpleName()) + ".";

   private final String countryName;

   private final String currencyName;

   private final String codeA;

   private final Integer codeN;

   private final Integer decimals;

   ECurrencyNumCode(String countryName, String currencyName, String codeA, Integer codeN, Integer decimals) {
      this.countryName = countryName;
      this.currencyName = currencyName;
      this.codeA = codeA;
      this.codeN = codeN;
      this.decimals = decimals;
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

   public String getCountryName() {
      return countryName;
   }

   public String getCurrencyName() {
      return currencyName;
   }

   public String getCodeA() {
      return codeA;
   }

   public Integer getCodeN() {
      return codeN;
   }

   public Integer getDecimals() {
      return decimals;
   }

   public static boolean isValidCurrencyNumCode(int currencyNumCode) {
      for (ECurrencyNumCode code : ECurrencyNumCode.values()) {
         if (code.getCodeN() == currencyNumCode) {
            return true;
         }
      }
      return false;
   }

   public static boolean isValidObject(Object submittedValue) {
      String stringValue = String.valueOf(submittedValue);
      if (isParsable(stringValue)) {
         Integer intVal = createInteger(stringValue);
         return ECurrencyNumCode.isValidCurrencyNumCode(intVal);
      } else {
         return false;
      }
   }

   @UtilityClass
   protected static class Constants {

      public static final String USD = "USD";

      public static final String EUR = "EUR";

   }
}
