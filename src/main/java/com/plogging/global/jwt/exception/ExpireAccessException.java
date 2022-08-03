package com.plogging.global.jwt.exception;

import org.springframework.http.HttpStatus;

public class ExpireAccessException extends JwtException{
    public ExpireAccessException() {
        super(
                HttpStatus.NOT_FOUND.toString(), HttpStatus.NOT_FOUND, "토큰을 찾지 못했습니다"
                                );
    }
}
