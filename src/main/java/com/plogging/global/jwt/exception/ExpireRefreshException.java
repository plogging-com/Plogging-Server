package com.plogging.global.jwt.exception;

import org.springframework.http.HttpStatus;

public class ExpireRefreshException extends JwtException{
    public ExpireRefreshException() {
        super(HttpStatus.NOT_FOUND.toString(),
                HttpStatus.NOT_FOUND,
                "Refresh Token 만료 되었습니다.");
    }

}
