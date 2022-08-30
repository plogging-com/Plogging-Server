package com.plogging.domain.Board.exception.Comment;

import com.plogging.global.exception.ApplicationException;
import org.springframework.http.HttpStatus;

public class CommentException extends ApplicationException {
    public CommentException(String errorCode, HttpStatus httpStatus, String message) {
        super(errorCode, httpStatus, message);
    }
}
