package com.capitole.security;

import static com.capitole.exceptions.CapitoleExceptionCode.ACCESS_DENIED;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.capitole.exceptions.CapitoleException;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class UserAuditInterceptor implements HandlerInterceptor {

   @Override
   public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws CapitoleException {
      Security resourceObj;
      if (handler instanceof HandlerMethod) {
         HandlerMethod method = (HandlerMethod) handler;
         resourceObj = method.getMethodAnnotation(Security.class);
         if (resourceObj == null) {
            return true;
         }
      } else {
         return true;
      }

      EResource eResource = resourceObj.value();
      UserContent subject = GlobalContext.get().getContent();
      if (!validatePermissions(eResource, subject)) {
         throw new CapitoleException(ACCESS_DENIED, eResource.getResourceId());
      }
      return true;
   }

   @Override
   public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView)
         throws Exception {
      //log parameters
   }

   private boolean validatePermissions(EResource eResource, UserContent subject) {
      //evaluate permissions
      return true;
   }
}
