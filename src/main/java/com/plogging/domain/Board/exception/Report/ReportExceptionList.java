package com.plogging.domain.Board.exception.Report;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Getter
@RequiredArgsConstructor
public enum ReportExceptionList {

    NOT_FOUND_ID("U0035", NOT_FOUND,"존재하지 않는 Board ID 입니다."),
    ALREAD_STATUS("U0036", BAD_REQUEST,"이미 같은 상태로 처리되어 있는 신고 입니다. (처리중, 처리완료 둘 중 다른 것으로 다시 요청하세요)");

    private final String CODE;
    private final HttpStatus HTTPSTATUS;
    private final String MESSAGE;
}
