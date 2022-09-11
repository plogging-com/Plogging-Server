package com.plogging.domain.User.exception;

import org.springframework.http.HttpStatus;

import static com.plogging.domain.User.exception.UserExceptionList.*;

public class NotFoundUserException extends UserException{
    public NotFoundUserException() {
        super(
                NOT_FOUND_USER_EXCEPTION.getCODE(),
                NOT_FOUND_USER_EXCEPTION.getHTTPSTATUS(),
                NOT_FOUND_USER_EXCEPTION.getMESSAGE());
    }
}
