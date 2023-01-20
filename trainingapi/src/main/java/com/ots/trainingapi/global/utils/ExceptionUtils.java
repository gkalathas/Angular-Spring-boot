package com.ots.trainingapi.global.utils;

import com.ots.trainingapi.global.errors.ExceptionResponse;
import com.ots.trainingapi.global.errors.GlobalExceptionResponse;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * Βοηθητικές λειτουργίες που αφορούν Exceptions
 * Περιλαμβάνονται στατικές μέθοδοι
 */
public class ExceptionUtils {
    
    /**
     * Δημιουργία αντικειμένου response με τα βασικά στοιχεία του exception
     * @param ex      αντικείμενο exception
     * @param request αντικείμενο http request
     * @return
     */
    public static ExceptionResponse getExceptionResponse(Exception ex, WebRequest request) {
        
        //Δημιουργία αναγνωριστικού σφάλματος
        String errorId = UuidUtils.generateId();
        //Βασικό μήνυμα σφάλματος
        String errorMessage = (ex.getMessage() == null ? ex.toString() : ex.getMessage());
        
        //Url της κλήσης στο API από την οποία προήλθε το exception
        HttpServletRequest httpServletRequest = ((ServletWebRequest) request).getRequest();
        
        String url = httpServletRequest.getRequestURL().toString() +
                (httpServletRequest.getQueryString() == null ? "" : "?" + httpServletRequest.getQueryString());
        
        //Συνδεδεμένος χρήστης
        String username = UserUtils.getUserName();
        
        ThreadContext.put("errorId", errorId);
        ThreadContext.put("url", url);
        
        return new GlobalExceptionResponse(errorId, errorMessage, url, username, ex);
    }
}
