package com.plogging.domain.User.exception;

import org.springframework.http.HttpStatus;

public class UserIDValidException extends UserException{

    public UserIDValidException() {
        super(UserExceptionList.VALID_FORM_ID.getCODE(),
                UserExceptionList.VALID_FORM_ID.getHTTPSTATUS(),
                UserExceptionList.VALID_FORM_ID.getMESSAGE());
    }
}
