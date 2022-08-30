package com.plogging.domain.User.dto.request;


import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("회원탈퇴 요청을 위한 객체")
public class UserDeleteReq {

    @NotNull(message = "비밀번호를 입력해주세요.")
    private String password;

}
