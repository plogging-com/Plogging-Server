package com.plogging.domain.Quest.exception;

import com.plogging.global.exception.ApplicationException;
import org.springframework.http.HttpStatus;

public class QuestException extends ApplicationException {

    protected QuestException(String errorCode, HttpStatus httpStatus, String message) {
        super(errorCode, httpStatus, message);
    }
}
