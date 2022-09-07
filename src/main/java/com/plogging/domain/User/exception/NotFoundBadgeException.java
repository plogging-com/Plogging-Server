package com.plogging.domain.User.exception;

import org.springframework.http.HttpStatus;

public class NotFoundBadgeException extends UserException{
    public NotFoundBadgeException() {
        super(HttpStatus.NOT_FOUND.toString(), HttpStatus.NOT_FOUND, "해당뱃지를 찾을수 없습니다.");
    }
}
