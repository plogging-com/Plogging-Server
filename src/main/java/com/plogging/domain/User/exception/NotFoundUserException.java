package com.plogging.domain.User.exception;

import org.springframework.http.HttpStatus;

public class NotFoundUserException extends UserException{
    public NotFoundUserException() {
        super(HttpStatus.NOT_FOUND.toString(), HttpStatus.NOT_FOUND, "해당 유저를 찾을 수 없습니다.");
    }
}
