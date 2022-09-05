package com.company.exceptions;

/**
 * @author Artur Tomeyan
 * @date 05/09/2022
 */
public class NotGeneratedException extends RuntimeException {
    public NotGeneratedException() {
    }

    public NotGeneratedException(String message) {
        super(message);
    }
}