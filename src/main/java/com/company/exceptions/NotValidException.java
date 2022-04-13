package com.company.exceptions;

public class NotValidException extends RuntimeException{

    public NotValidException(String message) {
        super(message);
    }
}