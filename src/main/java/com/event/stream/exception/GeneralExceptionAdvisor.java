package com.event.stream.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GeneralExceptionAdvisor extends ResponseEntityExceptionHandler {


    private ResponseEntity<Object> handleBadRequest(String s) {
        return new ResponseEntity<>(s, HttpStatus.BAD_REQUEST);
    }
}
