package com.capitole.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
public class MvcConfig implements WebMvcConfigurer {

   private final UserAuditInterceptor userAuditInterceptor;

   @Override
   public void addInterceptors(InterceptorRegistry registry) {
      registry.addInterceptor(userAuditInterceptor);
   }
}
