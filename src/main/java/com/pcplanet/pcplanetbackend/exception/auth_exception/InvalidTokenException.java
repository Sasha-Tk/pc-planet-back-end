package com.pcplanet.pcplanetbackend.exception.auth_exception;

public class InvalidTokenException extends RuntimeException {
    public InvalidTokenException(String message) {
        super(message.contains("expired") ?
                message.replace("An error occurred while attempting to decode the Jwt: ", "") :
                "Invalid Jwt");
    }
}
