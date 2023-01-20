package com.ots.trainingapi.global.errors;

import java.util.List;

public class ValidationExceptionResponse {
    
    private String username;
    
    private List<String> errors;
    
    public ValidationExceptionResponse() {
    
    }
    
    public ValidationExceptionResponse(String username, List<String> errors) {
        this.username = username;
        this.errors = errors;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public List<String> getErrors() {
        return errors;
    }
    
    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
