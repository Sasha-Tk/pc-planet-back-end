package com.pcplanet.pcplanetbackend.exception.user_exception;

public class NoSuchUserException extends RuntimeException {
    public NoSuchUserException(String message) {
        super(message);
    }
}
