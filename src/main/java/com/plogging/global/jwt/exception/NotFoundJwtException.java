package com.plogging.global.jwt.exception;

import org.springframework.http.HttpStatus;

public class NotFoundJwtException extends JwtException{
     public NotFoundJwtException() {
            super(HttpStatus.NOT_FOUND.toString(),
                    HttpStatus.NOT_FOUND,
                    "토큰을 찾을수 없습니다.");
        }
}
