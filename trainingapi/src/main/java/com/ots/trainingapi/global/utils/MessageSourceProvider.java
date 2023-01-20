package com.ots.trainingapi.global.utils;

import java.util.Locale;

public interface MessageSourceProvider {
    
    /**
     * Ανάκτηση μηνύματος
     */
    String getMessage(String messageName);
    
    /**
     * Ανάκτηση μηνύματος με παραμέτρους
     */
    String getMessage(String messageName, Object[] args);
    
    /**
     * Ανάκτηση μηνύματος με παραμέτρους και παροχή default μηνύματος
     */
    String getMessage(String messageName, Object[] args, String defaultMessage);
    
    /**
     * Ανάκτηση μηνύματος με παραμέτρους και παροχή default μηνύματος καθορίζοντας και τη γλώσσα (Locale)
     */
    String getMessage(String messageName, Object[] args, String defaultMessage, Locale locale);
}
