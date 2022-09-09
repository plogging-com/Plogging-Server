package com.plogging.domain.User.dto.response;

import com.plogging.domain.User.entity.Badge;
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
@ApiModel(description = "특정조건시에 유저뱃지의 생성여부를 확인하기위한 위한 객체")
public class UserBadgeCreateRes {

    private Long userIdx;
    private String name;
    private String comment;

    public static UserBadgeCreateRes create(User user , Badge badge){

        return UserBadgeCreateRes.builder().userIdx(user.getId()).name(badge.getName()).comment(badge.getComment()).build();

    }




}
