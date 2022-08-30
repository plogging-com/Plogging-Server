package com.plogging.domain.User.dto.response;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "유저 정보 조회 응답 객체")
public class UserInfoRes {

    private Long level;
    private String nickname;
    private String grade;
    private String photo;

    private Long step;


}
