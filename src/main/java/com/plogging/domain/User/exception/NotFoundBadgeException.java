package com.plogging.domain.User.exception;

import org.springframework.http.HttpStatus;

import static com.plogging.domain.User.exception.UserExceptionList.*;

public class NotFoundBadgeException extends UserException{
    public NotFoundBadgeException() {
        super(NOT_FOUND_BADGE_EXCEPTION.getCODE(),
                NOT_FOUND_BADGE_EXCEPTION.getHTTPSTATUS(),
                NOT_FOUND_BADGE_EXCEPTION.getMESSAGE());
    }
}
