package com.plogging.domain.User.exception;

import org.springframework.http.HttpStatus;

public class ValidUserFindPagingException extends UserException{

    public ValidUserFindPagingException() {
        super(HttpStatus.BAD_REQUEST.toString(), HttpStatus.BAD_REQUEST, "페이징 입력이 잘못되었습니다");
    }

}
