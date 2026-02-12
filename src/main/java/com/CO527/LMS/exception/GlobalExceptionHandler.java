package com.CO527.LMS.exception;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Validation Errors
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleValidation(
            MethodArgumentNotValidException ex) {

        Map<String,String> errors = new HashMap<>();

        ex.getBindingResult()
          .getFieldErrors()
          .forEach(e ->
            errors.put(e.getField(), e.getDefaultMessage())
          );

        return new ResponseEntity<>(
            errors,
            HttpStatus.BAD_REQUEST
        );
    }

    // Email Exists
    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<String> handleUserExists(
            UserAlreadyExistsException ex) {

        return new ResponseEntity<>(
            ex.getMessage(),
            HttpStatus.CONFLICT
        );
    }
}

