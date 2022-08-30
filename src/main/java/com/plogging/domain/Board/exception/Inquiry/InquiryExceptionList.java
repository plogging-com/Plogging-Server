package com.plogging.domain.Board.exception.Inquiry;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Getter
@RequiredArgsConstructor
public enum InquiryExceptionList {
    CANT_DELETE_INQUIRY("U0045", NOT_FOUND, "게시글 조회 기록을 삭제 할 수 없습니다. 사전에 게시글을 조회 한 경우에만 삭제 가능합니다.");

    private final String CODE;
    private final HttpStatus HTTPSTATUS;
    private final String MESSAGE;
}
