package com.ots.trainingapi.global.utils;

import com.ots.trainingapi.global.exceptions.ValidationException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Βοηθητικές μέθοδοι σχετικές με το validation
 */
public class ValidationUtils {
    
    /**
     * Μέθοδος που δέχεται μια λίστα με μηνύματα σφάλματος και, σε περίπτωση που η λίστα δεν είναι κενή,
     * πετάει ValidationException με αυτή τη λίστα σφαλμάτων.
     * @param errors
     */
    public static void throwIfExistsValidationException(List<String> errors) throws ValidationException {
        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }
    }
    
    /**
     * Έλεγχος εγκυρότητας αριθμητικής τιμής
     * @param value
     * @param errorMessages
     * @param messageSourceProvider
     */
    public static void checkNumberValue(String value, List<String> errorMessages, MessageSourceProvider messageSourceProvider) {
        try {
            Long.parseLong(value);
        }
        catch (NumberFormatException e) {
            errorMessages.add(value + ": " + messageSourceProvider.getMessage("error.validation.number"));
        }
    }
    
    /**
     * Έλεγχος εγκυρότητας τιμής dropdown
     * @param value
     * @param messageSourceProvider
     * @param optionValue
     * @param errorMessages
     */
    public static void checkDropdownValue(String value, String optionValue, List<String> errorMessages, MessageSourceProvider messageSourceProvider) {
        if (optionValue != null && optionValue.equals("id")) {
            try {
                Long.parseLong(value);
            }
            catch (NumberFormatException e) {
                errorMessages.add(value + ": " + messageSourceProvider.getMessage("error.validation.dropdown"));
            }
        }
    }
    
    /**
     * Έλεγχος εγκυρότητας ημερομηνίας
     * @param value
     * @param errorMessages
     * @param messageSourceProvider
     */
    public static void checkDateValue(String value, List<String> errorMessages, MessageSourceProvider messageSourceProvider) {
        String ddMMyyyRegex = "^[0-3][0-9]/[0-1][0-9]/(?:[0-9][0-9])?[0-9][0-9]$";
        
        if (!value.matches(ddMMyyyRegex)) {
            errorMessages.add(value + ": " + messageSourceProvider.getMessage("error.validation.date"));
        }
        else {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                sdf.setLenient(false);
                sdf.parse(value);
            }
            catch (ParseException e) {
                e.printStackTrace();
                errorMessages.add(value + ": " + messageSourceProvider.getMessage("error.validation.date"));
            }
        }
    }
    
    /**
     * Έλεγχος εγκυρότητας ημερομηνίας/ώρας
     * @param value
     * @param errorMessages
     * @param messageSourceProvider
     */
    public static void checkDateTimeValue(String value, List<String> errorMessages, MessageSourceProvider messageSourceProvider) {
        String ddMMyyyRegex = "^[0-3][0-9]/[0-1][0-9]/(?:[0-9][0-9])?[0-9][0-9] ([01]?[0-9]|2[0-3]):[0-5][0-9]$";
        
        if (!value.matches(ddMMyyyRegex)) {
            errorMessages.add(value + ": " + messageSourceProvider.getMessage("error.validation.datetime"));
        }
        else {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                sdf.setLenient(false);
                sdf.parse(value);
            }
            catch (ParseException e) {
                e.printStackTrace();
                errorMessages.add(value + ": " + messageSourceProvider.getMessage("error.validation.datetime"));
            }
        }
    }
    
    /**
     * Έλεγχος εγκυρότητας τιμής enum
     * @param value
     * @param enumClass
     * @param errorMessages
     * @param messageSourceProvider
     */
    public static void checkEnumValue(String value, String enumClass, List<String> errorMessages, MessageSourceProvider messageSourceProvider) {
        
        Boolean foundEnumMatch = false;
        
        try {
            Class<?> clazz = Class.forName("com.ots.trainingapi." + enumClass);
            
            for (Object obj : clazz.getEnumConstants()) {
                Enum<?> enumeration = (Enum<?>) obj;
                String enumValue = enumeration.name();
                Integer enumOrdinal = enumeration.ordinal();
                
                if (enumValue.equals(value)) {
                    foundEnumMatch = true;
                    break;
                }
                else if (enumOrdinal.toString().equals(value)) {
                    foundEnumMatch = true;
                    break;
                }
            }
            
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        
        if (!foundEnumMatch) {
            errorMessages.add(value + ": " + messageSourceProvider.getMessage("error.validation.enum"));
        }
    }
    
    /**
     * Έλεγχος εγκυρότητας τιμής true/false
     * @param value
     * @param errorMessages
     * @param messageSourceProvider
     */
    public static void checkYesNoValue(String value, List<String> errorMessages, MessageSourceProvider messageSourceProvider) {
        if (!value.equals("true") && !value.equals("false")) {
            errorMessages.add(value + ": " + messageSourceProvider.getMessage("error.validation.yesNo"));
        }
    }
    
    /**
     * Έλεγχος εγκυρότητας τιμής ΑΦΜ
     * @param value
     * @param errorMessages
     * @param messageSourceProvider
     */
    public static void checkAfmValue(String value, List<String> errorMessages, MessageSourceProvider messageSourceProvider, String messageDetails) {
        
        if (value == null) {
            return;
            
        }
        else {
            boolean tnrIsValid = false;
            String tnr = value.trim();
            
            if (tnr.isEmpty()) {
                tnrIsValid = true;
            }
            else {
                int sum = 0, last = 0, mod = 0;
                
                if (tnr.length() == 9 && tnr.matches("\\d+")) {
                    last = Integer.parseInt(tnr.substring(8));
                    for (int i = 1; i <= 8; i++) {
                        sum += Integer.parseInt(tnr.substring(i - 1, i)) * Math.pow(2, (9 - i));
                    }
                    mod = sum % 11;
                    if (String.valueOf(mod).substring(String.valueOf(mod).length() - 1).equals(String.valueOf(last))) {
                        tnrIsValid = true;
                    }
                }
            }
            
            if (!tnrIsValid) {
                errorMessages.add(value + ": " + messageSourceProvider.getMessage("error.common.invalidAfm", new Object[]{messageDetails}));
            }
        }
    }
}
