package com.plogging.domain.User.exception;

import org.springframework.http.HttpStatus;

public class NotFirstPloggingException extends UserException{
    public NotFirstPloggingException() {
        super(HttpStatus.CONFLICT.toString(), HttpStatus.CONFLICT, "해당 유저는 처음 플로깅을 시작하지 않았습니다.");
    }
}
