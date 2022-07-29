package com.plogging.domain.Quest.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum QuestExceptionList {

    ;

    private final String CODE;
    private final HttpStatus HTTPSTATUS;
    private final String MESSAGE;
}
