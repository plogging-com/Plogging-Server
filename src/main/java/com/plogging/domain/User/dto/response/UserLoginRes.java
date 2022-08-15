package com.plogging.domain.User.dto.response;

import com.plogging.domain.User.entity.User;
import io.swagger.annotations.ApiModel;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "로그인 응답을 위한 객체")
public class UserLoginRes {

    private String nickname;
    private Long userIdx;
    private String accessToken;
    private Long refreshTokenIdx;

    public static UserLoginRes from (User user , String accessToken , Long refreshTokenIdx) {
        return UserLoginRes.builder()
                .userIdx(user.getId())
                .nickname(user.getNickName())
                .accessToken(accessToken)
                .refreshTokenIdx(refreshTokenIdx).
                build();
    }

}
