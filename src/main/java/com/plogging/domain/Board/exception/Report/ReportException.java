package com.plogging.domain.Board.exception.Report;

import com.plogging.global.exception.ApplicationException;
import org.springframework.http.HttpStatus;

public class ReportException extends ApplicationException {
    public ReportException(String errorCode, HttpStatus httpStatus, String message) {
        super(errorCode, httpStatus, message);
    }
}
