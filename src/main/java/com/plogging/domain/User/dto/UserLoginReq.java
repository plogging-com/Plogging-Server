package com.plogging.domain.User.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("로그인 요청을 위한 객체")
public class UserLoginReq {

    @NotBlank
    private String loginId;

    @NotBlank
    private String password;

}
