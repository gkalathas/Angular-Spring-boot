package com.ots.trainingapi.global.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

@Component
@Scope("prototype")
public class MessageSourceProviderImpl implements MessageSourceProvider {
    
    @Autowired
    private MessageSource messageSource;
    
    @Autowired
    private LocaleResolver localeResolver;
    
    
    @Override
    public String getMessage(String messageName) {
        return messageSource.getMessage(messageName, new Object[]{}, getLocaleByHttpRequest());
    }
    
    @Override
    public String getMessage(String messageName, Object[] args) {
        return messageSource.getMessage(messageName, args, getLocaleByHttpRequest());
    }
    
    @Override
    public String getMessage(String messageName, Object[] args, String defaultMessage) {
        return messageSource.getMessage(messageName, args, defaultMessage, getLocaleByHttpRequest());
    }
    
    @Override
    public String getMessage(String messageName, Object[] args, String defaultMessage, Locale locale) {
        return messageSource.getMessage(messageName, args, defaultMessage, locale);
    }
    
    private Locale getLocaleByHttpRequest() {
        if(RequestContextHolder.getRequestAttributes() != null) {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            if(request != null) {
                return localeResolver.resolveLocale(request);
            }
        }
        
        return ((AcceptHeaderLocaleResolver) localeResolver).getDefaultLocale();
    }
}