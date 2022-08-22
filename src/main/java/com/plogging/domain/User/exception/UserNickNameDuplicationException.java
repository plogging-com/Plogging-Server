package com.plogging.domain.User.exception;

import org.springframework.http.HttpStatus;

public class UserNickNameDuplicationException extends UserException{

    public UserNickNameDuplicationException() {
        super(UserExceptionList.DUPLICATION_NICKNAME.getCODE(),
                UserExceptionList.DUPLICATION_NICKNAME.getHTTPSTATUS(),
                UserExceptionList.DUPLICATION_NICKNAME.getMESSAGE());
    }
}
