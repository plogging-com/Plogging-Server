package com.plogging.domain.Board.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;


@Getter
@RequiredArgsConstructor
public enum BoardExceptionList {

    ;

    private final String CODE;
    private final HttpStatus HTTPSTATUS;
    private final String MESSAGE;

}
