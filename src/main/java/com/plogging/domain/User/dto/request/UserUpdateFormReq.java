package com.plogging.domain.User.dto.request;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("유저정보 수정폼 요청을 위한 객체")
public class UserUpdateFormReq {

    private Long userIdx;

}
