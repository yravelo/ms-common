package com.capitole.security;

import static java.lang.ThreadLocal.withInitial;
import static java.util.Locale.ENGLISH;

import java.util.HashMap;

import lombok.experimental.UtilityClass;

@UtilityClass
public class GlobalContext {

   private static final ThreadLocal<UserContext> t = withInitial(
         () -> UserContext.builder().locale(ENGLISH).content(new UserContent()).paramMap(new HashMap<>()).build());

   public static UserContext get() {
      return t.get();
   }

}
