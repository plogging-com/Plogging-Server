package com.plogging.domain.User.exception;

import org.springframework.http.HttpStatus;

import static com.plogging.domain.User.exception.UserExceptionList.*;

public class UserPasswordValidException extends UserException{
    public UserPasswordValidException() {
        super(
                VALID_FORM_PASSWORD.getCODE(), VALID_FORM_PASSWORD.getHTTPSTATUS(), VALID_FORM_PASSWORD.getMESSAGE());
    }
}
