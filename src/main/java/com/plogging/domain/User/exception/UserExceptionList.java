package com.plogging.domain.User.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@RequiredArgsConstructor
public enum UserExceptionList {

    DUPLICATION_ID("U0001", CONFLICT,"이미 존재하는 아이디입니다."),
    DUPLICATION_NICKNAME("U0002", CONFLICT, "이미 존재하는 닉네임 입니다."),
    VALID_FORM_ID("U0003",CONFLICT,"로그인 Id는 5~12글자의 영소문자, 숫자만 가능합니다."),
    VALID_FORM_NICKNAME("U0004",CONFLICT,"닉네임은 2~5글자의 영소문자, 숫자, 한글만 가능합니다."),
    PASSWORD_WRONG("U0005", CHECKPOINT, "비밀번호를 확인해주세요.");

    private final String CODE;
    private final HttpStatus HTTPSTATUS;
    private final String MESSAGE;

}
