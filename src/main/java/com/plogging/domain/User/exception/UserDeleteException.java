package com.plogging.domain.User.exception;

import org.springframework.http.HttpStatus;

import static com.plogging.domain.User.exception.UserExceptionList.DELETE_USER;

public class UserDeleteException extends UserException{
    public UserDeleteException() {
        super(DELETE_USER.getCODE(), DELETE_USER.getHTTPSTATUS(), DELETE_USER.getMESSAGE());
    }
}
