package com.plogging.domain.User.dto.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "회원가입 응답 객체")
public class UserJoinRes {

    private String username;
}
