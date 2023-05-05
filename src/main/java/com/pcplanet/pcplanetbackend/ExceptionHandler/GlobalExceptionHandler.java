package com.pcplanet.pcplanetbackend.ExceptionHandler;

import com.pcplanet.pcplanetbackend.Exception.ExceptionMessage;
import com.pcplanet.pcplanetbackend.Exception.InvalidTokenException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<ExceptionMessage> handleException(InvalidTokenException e) {
        return new ResponseEntity<>(
                new ExceptionMessage(
                        "Jwt token authentication failed",
                        e.getMessage()),
                HttpStatus.UNAUTHORIZED);
    }
}
