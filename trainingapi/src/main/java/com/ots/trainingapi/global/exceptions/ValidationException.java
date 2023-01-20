package com.ots.trainingapi.global.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationException extends RuntimeException {
    
    private List<String> errors = new ArrayList<>();
    
    public ValidationException() {
    }
    
    public ValidationException(String error) {
        errors.add(error);
    }
    
    public ValidationException(List<String> errors) {
        this.errors = errors;
    }
    
    public List<String> getErrors() {
        return errors;
    }
    
    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
