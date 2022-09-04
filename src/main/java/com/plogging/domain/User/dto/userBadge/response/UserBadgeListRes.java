package com.plogging.domain.User.dto.userBadge.response;

import com.plogging.domain.User.entity.Badge;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "유저뱃지 리스트 응답을 위한 객체")
public class UserBadgeListRes {

    private Long id;
    private String name;
    private String photo;
    private String comment;

    public static UserBadgeListRes create(Badge badge){
        return UserBadgeListRes.builder()
                .id(badge.getId())
                .name(badge.getName())
                .photo(badge.getPhoto())
                .comment(badge.getComment()).build();
    }

}
