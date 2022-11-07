package com.capitole.security;

import java.util.Locale;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserContext {

   private UserContent content;

   private Locale locale;

   private Object[] params;

   private String[] paramNames;

   private Map<String, Object> paramMap;

}
