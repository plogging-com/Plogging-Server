package com.plogging.domain.Board.exception.Inquiry;

import com.plogging.global.exception.ApplicationException;
import org.springframework.http.HttpStatus;

public class InquiryException extends ApplicationException {
    public InquiryException(String errorCode, HttpStatus httpStatus, String message) {
        super(errorCode, httpStatus, message);
    }
}
