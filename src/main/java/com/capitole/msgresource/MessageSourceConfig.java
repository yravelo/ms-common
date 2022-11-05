package com.capitole.msgresource;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
public class MessageSourceConfig {

   @Bean
   public MessageSource messageSource() {
      ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
      //@formatter:off
      messageSource.setBasenames("classpath:/com/capitole/enums/enum",
                                 "classpath:com/capitole/validations/validationmessage");
      //@formatter:on
      return messageSource;
   }

}
