package com.plogging.domain.Quest.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Getter
@RequiredArgsConstructor
public enum QuestExceptionList {

    NOT_FOUND_ID("U0011", NOT_FOUND,"존재하지 않는 Quest ID 입니다.."),
    NOT_FOUND_NAME("U0012", NOT_FOUND,"존재하지 않는 Quest Name 입니다."),
    ALL_EMPTY("U0013", NOT_FOUND,"Quest 데이터가 존재하지 않습니다. Quest 엔티티를 데이터베이스에 추가한 후 요청해주세요.");

    private final String CODE;
    private final HttpStatus HTTPSTATUS;
    private final String MESSAGE;
}
