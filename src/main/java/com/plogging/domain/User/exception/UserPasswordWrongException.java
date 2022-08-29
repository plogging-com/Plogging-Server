package com.plogging.domain.User.exception;

import org.springframework.http.HttpStatus;

public class UserPasswordWrongException extends UserException{
    public UserPasswordWrongException() {
        super(UserExceptionList.PASSWORD_WRONG.getCODE(), UserExceptionList.PASSWORD_WRONG.getHTTPSTATUS(), UserExceptionList.PASSWORD_WRONG.getMESSAGE());
    }
}
