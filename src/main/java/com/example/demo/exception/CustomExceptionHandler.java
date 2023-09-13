package com.example.demo.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.LinkedHashMap;
import java.util.Map;

// @ControllerAdvice is used to handle exceptions globally
/**
 * if an exception is thrown in, for example, AuthorController or AuthorService and not taken care, it will be handled
 * by the CustomExceptionHandler, it will return a response entity to the user, with the status code, and the exception
 * it takes care of Exception class, which is the parent of all exceptions, so it will handle all exceptions
 * see @ExceptionHandler(Exception.class)
 */
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler
{

// @ExceptionHandler is used to handle exceptions, and return a response to the user
    // it takes the exception class as a parameter, and the request, and returns a response entity
    // it returns a response entity, which contains the status code, and the exception message
    // it is used together with @ControllerAdvice, to handle exceptions globally
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("timestamp", System.currentTimeMillis());
        response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.put("error", "Internal Server Error");
        response.put("path", request.getDescription(false));
        response.put("message", "An error occurred while processing your request.");
        response.put("details", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
