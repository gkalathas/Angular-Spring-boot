package com.ots.trainingapi.global.handlers;

import com.ots.trainingapi.global.errors.ValidationExceptionResponse;
import com.ots.trainingapi.global.exceptions.ValidationException;
import com.ots.trainingapi.global.utils.UserUtils;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ValidationExceptionHandler extends ResponseEntityExceptionHandler {
    
    @ExceptionHandler(value = {ValidationException.class})
    protected ResponseEntity<Object> handleValidationException(ValidationException ex, WebRequest request) {
    
        ValidationExceptionResponse response = new ValidationExceptionResponse();
        
        //Συνδεδεμένος χρήστης
        response.setUsername(UserUtils.getUserName());
        
        response.setErrors(ex.getErrors());
        
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/json; charset=utf-8");
        
        return new ResponseEntity<>(response, headers, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
