package com.plogging.global.jwt.exception;

import com.plogging.global.exception.ApplicationException;
import org.springframework.http.HttpStatus;

public abstract class JwtException extends ApplicationException {
    protected JwtException(String errorCode, HttpStatus httpStatus, String message) {
        super(errorCode, httpStatus, message);
    }
}
