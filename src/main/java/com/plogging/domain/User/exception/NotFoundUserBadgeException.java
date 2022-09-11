package com.plogging.domain.User.exception;

import org.springframework.http.HttpStatus;

import static com.plogging.domain.User.exception.UserExceptionList.*;

public class NotFoundUserBadgeException  extends UserException{
    public NotFoundUserBadgeException() {
        super(
                NOT_FOUND_USER_BADGE_EXCEPTION.getCODE(),
                NOT_FOUND_USER_EXCEPTION.getHTTPSTATUS(),
                NOT_FOUND_USER_BADGE_EXCEPTION.getMESSAGE()
        );
    }
}
