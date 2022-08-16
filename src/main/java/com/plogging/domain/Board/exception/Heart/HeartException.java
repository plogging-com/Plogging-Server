package com.plogging.domain.Board.exception.Heart;

import com.plogging.global.exception.ApplicationException;
import org.springframework.http.HttpStatus;

public class HeartException extends ApplicationException {
    protected HeartException(String errorCode, HttpStatus httpStatus, String message) {
        super(errorCode, httpStatus, message);
    }
}