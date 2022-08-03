package com.plogging.domain.User.dto;

import io.swagger.annotations.ApiModel;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "로그인 응답을 위한 객체")
public class UserLoginRes {

    private String name;
    private String accessToken;
    private Long refreshTokenIdx;

}
