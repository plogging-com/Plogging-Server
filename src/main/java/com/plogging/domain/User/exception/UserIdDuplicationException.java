package com.plogging.domain.User.exception;

import org.springframework.http.HttpStatus;

public class UserIdDuplicationException extends UserException{

    public UserIdDuplicationException() {
        super(UserExceptionList.DUPLICATION_ID.getCODE(),
                UserExceptionList.DUPLICATION_ID.getHTTPSTATUS(),
                UserExceptionList.DUPLICATION_ID.getMESSAGE());
    }
}
