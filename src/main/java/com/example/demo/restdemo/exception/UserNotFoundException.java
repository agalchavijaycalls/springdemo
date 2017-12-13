package com.example.demo.restdemo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends AuthenticationException {

    public UserNotFoundException(String userId) {
        super("could not find user '" + userId + "'.");
    }
}