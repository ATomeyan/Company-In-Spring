package com.company.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class AdviserController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotValidException.class)
    public ResponseEntity<Object> handleNotValidException(NotValidException e) {

        ExceptionResponse exceptionResponse = new ExceptionResponse();

        exceptionResponse.setDateTime(LocalDateTime.now());
        exceptionResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        exceptionResponse.setError(HttpStatus.BAD_REQUEST.getReasonPhrase());
        exceptionResponse.setMessage(e.getMessage());

        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<Object> handleAlreadyExistException(AlreadyExistsException e) {
        ExceptionResponse exceptionResponse = new ExceptionResponse();

        exceptionResponse.setDateTime(LocalDateTime.now());
        exceptionResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        exceptionResponse.setError(HttpStatus.BAD_REQUEST.getReasonPhrase());
        exceptionResponse.setMessage(e.getMessage());

        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(NotFoundException e) {
        ExceptionResponse exceptionResponse = new ExceptionResponse();

        exceptionResponse.setDateTime(LocalDateTime.now());
        exceptionResponse.setStatus(HttpStatus.NOT_FOUND.value());
        exceptionResponse.setError(HttpStatus.NOT_FOUND.getReasonPhrase());
        exceptionResponse.setMessage(e.getMessage());

        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserAuthenticationException.class)
    public ResponseEntity<Object> handleUserAuthenticationException(UserAuthenticationException e) {
        ExceptionResponse exceptionResponse = new ExceptionResponse();

        exceptionResponse.setDateTime(LocalDateTime.now());
        exceptionResponse.setStatus(HttpStatus.FORBIDDEN.value());
        exceptionResponse.setError(HttpStatus.FORBIDDEN.getReasonPhrase());
        exceptionResponse.setMessage(e.getMessage());

        return new ResponseEntity<>(exceptionResponse, HttpStatus.FORBIDDEN);
    }
}