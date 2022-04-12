package com.company.controller;

import com.company.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class AdviserController extends ResponseEntityExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleEmployeeNotValidException(NotFoundException e, WebRequest request) {

        ExceptionResponse body = new ExceptionResponse();
        body.setTimestamp(LocalDateTime.now())
                .setStatus(HttpStatus.BAD_REQUEST.value())
                .setError(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .setMessage(e.getMessage());

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
}