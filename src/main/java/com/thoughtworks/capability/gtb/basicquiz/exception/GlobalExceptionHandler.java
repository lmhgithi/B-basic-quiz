package com.thoughtworks.capability.gtb.basicquiz.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.security.InvalidParameterException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(InvalidParameterException.class)
    public ResponseEntity handleInvalidException(InvalidParameterException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
