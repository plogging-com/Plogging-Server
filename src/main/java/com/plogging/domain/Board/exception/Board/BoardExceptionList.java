package com.plogging.domain.Board.exception.Board;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;


@Getter
@RequiredArgsConstructor
public enum BoardExceptionList {

    NOT_FOUND_ID("U0021", NOT_FOUND,"존재하지 않는 Board ID 입니다."),
    MAX_CONTENTSIZE("U0023", BAD_REQUEST, "게시글 내용(content)의 글자수는 700자를 넘길 수 없습니다."),
    MIN_CATEGORY("U0025", BAD_REQUEST, "카테고리는 최소 1개 이상이어야 합니다. categoryName1 부터 카테고리를 등록해 주세요");

    private final String CODE;
    private final HttpStatus HTTPSTATUS;
    private final String MESSAGE;

}
