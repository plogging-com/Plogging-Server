package com.plogging.domain.User.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "유저 검색을 위한 객체")
public class UserFindReq {

    @ApiModelProperty(value = "페이지 번호(1...N)")
    int pg; //현재 페이지

    @ApiModelProperty(value = "페이지 크기(1...N)" , allowableValues = "range[1,]")
    int sz; //한번에 몇개를 가져올건지;

    @ApiModelProperty(value = "사용자 닉네임" , required = true)
    String userNickName;

}

