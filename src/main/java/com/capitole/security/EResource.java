package com.capitole.security;

import static com.capitole.security.EActivityType.CREATE;
import static com.capitole.security.EActivityType.QUERY;
import static com.capitole.security.EActivityType.REMOVE;
import static com.capitole.security.EActivityType.UPDATE;

import org.apache.logging.log4j.Logger;

import com.capitole.enums.IEnumResource;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Getter
public enum EResource implements IEnumResource {
   UNKNOWN(0L),
   PRICES(101L),
   PRICES_QUERY(102L, PRICES, QUERY),
   PRODUCT(110L),
   PRODUCT_CREATE(111L, PRODUCT, CREATE, false, false),
   PRODUCT_UPDATE(112L, PRODUCT, UPDATE, false, false),
   PRODUCT_REMOVE(113L, PRODUCT, REMOVE, false, false),
   PRODUCT_LIST_ALL(114L, PRODUCT, QUERY),
   ROLE_BY_ID(115L, PRODUCT, QUERY);

   private static final String RESOURCE_FILE = "com/capitole/resources/resource";

   private final Long resourceId;

   private final EResource parent;

   private final EActivityType activityType;

   private final Boolean anonymous;

   private final Boolean logActivity;

   EResource(Long resourceId) {
      this.resourceId = resourceId;
      this.parent = null;
      this.activityType = EActivityType.UNKNOWN;
      this.anonymous = false;
      this.logActivity = true;
   }

   EResource(Long resourceId, EResource parent, EActivityType activityType) {
      this.resourceId = resourceId;
      this.parent = parent;
      this.activityType = activityType;
      this.anonymous = false;
      this.logActivity = true;
   }

   EResource(Long resourceId, EResource parent, EActivityType activityType, Boolean anonymous, Boolean logActivity) {
      this.resourceId = resourceId;
      this.parent = parent;
      this.activityType = activityType;
      this.anonymous = anonymous;
      this.logActivity = logActivity;
   }

   @Override
   public String getEnumKey() {
      return this.name();
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
      return RESOURCE_FILE;
   }

   public static EResource getByValue(Long value) {
      for (EResource eResource : values()) {
         if (eResource.getResourceId().equals(value)) {
            return eResource;
         }
      }
      return UNKNOWN;
   }

}
