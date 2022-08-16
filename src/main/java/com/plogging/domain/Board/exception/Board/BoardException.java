package com.plogging.domain.Board.exception.Board;

import com.plogging.global.exception.ApplicationException;
import org.springframework.http.HttpStatus;

public class BoardException extends ApplicationException {
    protected BoardException(String errorCode, HttpStatus httpStatus, String message) {
        super(errorCode, httpStatus, message);
    }
}
