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
@ApiModel(description = "로그인 응답을 위한 객체")
public class UserUpdateFormRes {

    private String nickname;
    private Long userIdx;
    private String photo;

    public static UserUpdateFormRes create(User user) {
        return UserUpdateFormRes.builder()
                .userIdx(user.getId())
                .nickname(user.getNickName())
                .photo(user.getPhoto()).build();
    }


}
