package com.pcplanet.pcplanetbackend.exception.auth_exception;

public class EmailAlreadyExistException extends RuntimeException{
    public EmailAlreadyExistException(String message) {
        super(message);
    }
}
