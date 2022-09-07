package com.plogging.domain.User.exception;

import org.springframework.http.HttpStatus;

public class NotFoundUserBadgeException  extends UserException{
    protected NotFoundUserBadgeException() {
        super(HttpStatus.NOT_FOUND.toString(), HttpStatus.NOT_FOUND, "해당뱃지를 찾을수 없습니다.");
    }
}
