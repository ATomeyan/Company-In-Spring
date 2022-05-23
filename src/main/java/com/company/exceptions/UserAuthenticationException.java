package com.company.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class UserAuthenticationException extends AuthenticationException {
    public UserAuthenticationException(String msg) {
        super(msg);
    }
}