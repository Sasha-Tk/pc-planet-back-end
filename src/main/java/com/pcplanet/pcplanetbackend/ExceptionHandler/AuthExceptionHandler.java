package com.pcplanet.pcplanetbackend.ExceptionHandler;

import com.pcplanet.pcplanetbackend.Authentication.User.UserAuthController;
import com.pcplanet.pcplanetbackend.Exception.ExceptionMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.server.resource.web.authentication.BearerTokenAuthenticationFilter;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@RestControllerAdvice(assignableTypes = {UserAuthController.class, BearerTokenAuthenticationFilter.class})
public class AuthExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ExceptionMessage> handleException(IllegalStateException e) {
        return new ResponseEntity<>(new ExceptionMessage("Email already exist", e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ExceptionMessage> handleException(IllegalArgumentException e) {
        return new ResponseEntity<>(new ExceptionMessage("Invalid user data", e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({BadCredentialsException.class, AccountStatusException.class})
    public ResponseEntity<ExceptionMessage> handleException(AuthenticationException e) {
        return new ResponseEntity<>(new ExceptionMessage("Unauthorized", e.getMessage()), HttpStatus.UNAUTHORIZED);
    }
}
