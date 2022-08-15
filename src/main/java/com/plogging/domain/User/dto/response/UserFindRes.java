package com.plogging.domain.User.dto.response;

import com.plogging.domain.User.entity.User;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "유저 검색 요청 응답을 위한 객체")
public class UserFindRes {

    private Long userIdx;
    private String nickName;

    public static UserFindRes from (User user) {
        return UserFindRes.builder()
                .userIdx(user.getId())
                .nickName(user.getNickName()).build();
    }

}
