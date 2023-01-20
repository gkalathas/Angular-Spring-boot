package com.ots.trainingapi.global.utils;

import com.ots.trainingapi.config.ContextProvider;


public class EnumUtils {
    
    /**
     * Ανάκτηση του message που αντιστοιχεί στη δεδομένη τιμή enum
     */
    public static String getMessage(Enum<?> enumeration, String messageCode) {
        if(enumeration == null || TextUtils.isEmpty(messageCode)) {
            return null;
        }
        MessageSourceProvider messageSourceProvider = ContextProvider.getBean(MessageSourceProvider.class);
        if(messageSourceProvider == null) {
            return enumeration.name();
        }
        else {
            return messageSourceProvider.getMessage(messageCode + "." + enumeration.name());
        }
    }
}
