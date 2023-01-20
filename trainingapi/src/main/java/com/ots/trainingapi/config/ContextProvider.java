package com.ots.trainingapi.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Context provider
 * Κλάση με στατικές μεθόδους που παρέχει πρόσβαση στο ApplicationContext της εφαρμογής από non managed objects
 */
@Component
public class ContextProvider implements ApplicationContextAware {
    
    private static ApplicationContext APPLICATION_CONTEXT;
    
    /**
     * Ανάκτηση bean από το ApplicationContext με βάση το beanClass.
     * Χρησιμοποιείται για κλήση μεθόδων services, repositories από non managed objects.
     */
    public static <T> T getBean(Class<T> beanClass) {
        return APPLICATION_CONTEXT.getBean(beanClass);
    }
    
    /**
     * Ανάκτηση bean από το ApplicationContext με βάση το beanName.
     * Χρησιμοποιείται για κλήση μεθόδων services, repositories από non managed objects.
     */
    public static Object getBean(String beanName) {
        return APPLICATION_CONTEXT.getBean(beanName);
    }
    
    /**
     * Publish event to ApplicationContext event bus.
     * Χρησιμοποιείται για publishing γεγονότων από non managed objects.
     */
    public static void publishEvent(Object event) {
        APPLICATION_CONTEXT.publishEvent(event);
    }
    
    /**
     * Set ApplicationContext to static variable
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        APPLICATION_CONTEXT = applicationContext;
    }
    
    public static ApplicationContext getContext() {
        return APPLICATION_CONTEXT;
    }
}
