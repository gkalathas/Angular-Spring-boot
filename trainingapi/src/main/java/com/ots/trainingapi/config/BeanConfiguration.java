package com.ots.trainingapi.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.data.web.SortHandlerMethodArgumentResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import java.nio.charset.StandardCharsets;
import java.util.Locale;

/**
 * Κλάση στην οποία δηλώνονται διάφορα μεμονωμένα Beans.
 */
@Configuration
public class BeanConfiguration {
    
    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        
        //Default format ημερομηνίας
        //objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        //objectMapper.setDateFormat(new SimpleDateFormat("dd-MM-yyyy"));
        //Μη συμπερίληψη null τιμών στα JSON
        //objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return objectMapper;
    }
    
    @Bean
    public SortHandlerMethodArgumentResolver sortHandlerMethodArgumentResolver() {
        CustomSortHandlerMethodArgumentResolver sortResolver = new CustomSortHandlerMethodArgumentResolver();
        sortResolver.setSortParameter("sidx");
        sortResolver.setSortOrder("sord");
        return sortResolver;
    }
    
    @Bean
    public LocaleResolver localeResolver() {
        AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver();
        localeResolver.setDefaultLocale(new Locale("el"));
        return localeResolver;
    }
    
    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setDefaultEncoding(StandardCharsets.UTF_8.name());
        messageSource.setBasenames(
                "classpath:i18n/global/enum",
                "classpath:i18n/trn/campaign"
        );
        messageSource.setUseCodeAsDefaultMessage(true);
        
        return messageSource;
    }
}
