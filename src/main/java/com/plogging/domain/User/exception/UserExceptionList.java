package com.plogging.domain.User.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.HttpStatus.Series.CLIENT_ERROR;

@Getter
@RequiredArgsConstructor
public enum UserExceptionList {

    DUPLICATION_ID("U0001", CONFLICT,"이미 존재하는 아이디입니다."),
    DUPLICATION_NICKNAME("U0002", CONFLICT, "이미 존재하는 닉네임 입니다."),
    VALID_FORM_ID("U0003",CONFLICT,"로그인 Id는 5~12글자의 영소문자, 숫자만 가능합니다."),
    VALID_FORM_NICKNAME("U0004",CONFLICT,"닉네임은 2~5글자의 영소문자, 숫자, 한글만 가능합니다."),
    PASSWORD_WRONG("U0005", CHECKPOINT, "비밀번호를 확인해주세요."),
    TERMS_NOT_FOUND("U0006", NOT_FOUND,"조건 값이 올바르지 않습니다."),
    VALID_FORM_PASSWORD("U0007", CONFLICT,"비밀번호는 영문과 특수문자 숫자를 포함하여 8자 이상이어야 합니다."),
    DUPLICATION_PHONE("U0008", CONFLICT, "해당 번호로 이미 가입되어 있습니다."),
    DELETE_USER("U0009", CONFLICT, "탈퇴한 아이디입니다.");

    private final String CODE;
    private final HttpStatus HTTPSTATUS;
    private final String MESSAGE;

}
