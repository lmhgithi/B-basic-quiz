package com.thoughtworks.capability.gtb.entrancequiz.exception;

import com.thoughtworks.capability.gtb.entrancequiz.exception.dto.ErrorResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;


@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResult> handle(MethodArgumentNotValidException e) {
        String message = e.getBindingResult().getFieldError().getDefaultMessage();
        ErrorResult errorResult = new ErrorResult(Instant.now().toString(), 400, "Bad Request", message);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResult);
    }

    @ExceptionHandler(CommonException.class)
    public ResponseEntity<ErrorResult> handle(CommonException e) {
        String message = e.getMessage();
        ErrorResult errorResult = new ErrorResult(Instant.now().toString(), 404, "Not Found", message);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResult);
    }
}
