package com.plogging.domain.User.exception;

import org.springframework.http.HttpStatus;

public class UserNickNameValidException extends UserException{
    public UserNickNameValidException() {
        super(UserExceptionList.VALID_FORM_NICKNAME.getCODE(),
                UserExceptionList.VALID_FORM_NICKNAME.getHTTPSTATUS(),
                UserExceptionList.VALID_FORM_NICKNAME.getMESSAGE());
    }
}
