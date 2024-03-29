package com.plogging.global.jwt.exception;

import org.springframework.http.HttpStatus;

public class ExpireAccessException extends JwtException{
    public ExpireAccessException() {
        super(HttpStatus.NOT_FOUND.toString(),
                HttpStatus.NOT_FOUND,
                "Access Token 만료 되었습니다.");
    }

}
