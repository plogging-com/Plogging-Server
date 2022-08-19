package com.plogging.domain.Board.exception.Board;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;


@Getter
@RequiredArgsConstructor
public enum BoardExceptionList {

    NOT_FOUND_ID("U0021", NOT_FOUND,"존재하지 않는 Board ID 입니다."),
    MAX_CONTENTSIZE("U0023", BAD_REQUEST, "게시글 내용(content)의 글자수는 700자를 넘길 수 없습니다.");

    private final String CODE;
    private final HttpStatus HTTPSTATUS;
    private final String MESSAGE;

}
