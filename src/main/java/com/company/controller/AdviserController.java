package com.company.controller;

import com.company.exceptions.AlreadyExistsException;
import com.company.exceptions.ExceptionResponse;
import com.company.exceptions.NotFoundException;
import com.company.exceptions.NotValidException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class AdviserController extends ResponseEntityExceptionHandler {

    @ExceptionHandler({NotValidException.class})
    public ResponseEntity<Object> handleEmployeeNotValidException(NotValidException e) {

        ExceptionResponse exceptionResponse = new ExceptionResponse();

        exceptionResponse.setDateTime(LocalDateTime.now());
        exceptionResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        exceptionResponse.setError(HttpStatus.BAD_REQUEST.getReasonPhrase());
        exceptionResponse.setMessage(e.getMessage());

        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<Object> handleEmployeeAlreadyExistException(AlreadyExistsException e){
        ExceptionResponse exceptionResponse = new ExceptionResponse();

        exceptionResponse.setDateTime(LocalDateTime.now());
        exceptionResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        exceptionResponse.setError(HttpStatus.BAD_REQUEST.getReasonPhrase());
        exceptionResponse.setMessage(e.getMessage());

        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleEmployeeNotFoundException(NotFoundException e){
        ExceptionResponse exceptionResponse = new ExceptionResponse();

        exceptionResponse.setDateTime(LocalDateTime.now());
        exceptionResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        exceptionResponse.setError(HttpStatus.BAD_REQUEST.getReasonPhrase());
        exceptionResponse.setMessage(e.getMessage());

        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
}