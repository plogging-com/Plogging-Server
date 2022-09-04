package com.plogging.domain.User.exception;

import org.springframework.http.HttpStatus;

import static com.plogging.domain.User.exception.UserExceptionList.*;

public class UserDuplicationPhoneException extends UserException{
    public UserDuplicationPhoneException() {
        super(DUPLICATION_PHONE.getCODE(), DUPLICATION_PHONE.getHTTPSTATUS(), DUPLICATION_PHONE.getMESSAGE());
    }
}
