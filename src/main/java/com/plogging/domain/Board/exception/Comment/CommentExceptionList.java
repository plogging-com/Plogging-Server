package com.plogging.domain.Board.exception.Comment;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Getter
@RequiredArgsConstructor
public enum CommentExceptionList {

    EMPTY_CONTENT("U0040", BAD_REQUEST, "댓글의 내용(content)은 비어있을 수 없습니다. 내용을 입력해주세요"),
    NOT_FOUND_ID("U0041", NOT_FOUND,"존재하지 않는 Comment ID 입니다.");

    private final String CODE;
    private final HttpStatus HTTPSTATUS;
    private final String MESSAGE;
}
