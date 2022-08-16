package com.plogging.domain.Board.exception.Heart;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@RequiredArgsConstructor
public enum HeartExceptionList {

    ALREADY_HERATED("U0031", BAD_REQUEST, "이미 좋아요 되어 있습니다."),
    CANT_CANCEL_HEART("U0032", BAD_REQUEST, "좋아요를 취소 할 수 없습니다. 사전에 좋아요를 한 경우에만 취소 가능합니다.");

    private final String CODE;
    private final HttpStatus HTTPSTATUS;
    private final String MESSAGE;
}