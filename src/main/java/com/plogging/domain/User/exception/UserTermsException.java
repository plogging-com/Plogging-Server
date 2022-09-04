package com.plogging.domain.User.exception;

import org.springframework.http.HttpStatus;

public class UserTermsException extends UserException{

    public UserTermsException() {
        super(UserExceptionList.TERMS_NOT_FOUND.getCODE(), UserExceptionList.TERMS_NOT_FOUND.getHTTPSTATUS(), UserExceptionList.TERMS_NOT_FOUND.getMESSAGE());
    }
}
