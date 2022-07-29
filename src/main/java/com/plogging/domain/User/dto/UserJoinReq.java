package com.plogging.domain.User.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "회원가입 반환 객체")
public class UserJoinReq {

    private String id;

    private String password;

}
