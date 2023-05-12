package com.pcplanet.pcplanetbackend.exception_handler;

import com.pcplanet.pcplanetbackend.exception.ExceptionMessage;
import com.pcplanet.pcplanetbackend.exception.user_exception.NoSuchUserException;
import com.pcplanet.pcplanetbackend.user_information.UserInformationController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = {UserInformationController.class})
public class UserExceptionHandler {

    @ExceptionHandler(NoSuchUserException.class)
    public ResponseEntity<ExceptionMessage> handleException(NoSuchUserException e) {
        return new ResponseEntity<>(new ExceptionMessage("No element present User advice ", e.getMessage()), HttpStatus.UNAUTHORIZED);
    }
}
