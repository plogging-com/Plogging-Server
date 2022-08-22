package com.plogging.domain.User.dto.request;

import com.plogging.domain.User.entity.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.lang.NonNull;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "회원가입 반환 객체")
public class UserJoinReq {

    @NotBlank(message = "회원의 ID를 입력해주세요.")
//    @Pattern(regexp = "^[a-z0-9]{6,18}$", message = "로그인 Id는 6~18글자의 영소문자, 숫자만 가능합니다.")
    @ApiModelProperty(notes = "로그인 ID를 입력해주세요.")
    private String id;

    @NotBlank(message = "회원의 비밀번호를 입력해주세요.")
    @ApiModelProperty(notes = "회원의 비밀번호를 입력해 주세요.")
    private String password;

    @NotBlank(message = "회원의 전화번호를 입력해주세요.")
    @ApiModelProperty(notes = "회원의 전화번호를 입력해 주세요.")
    private String phone;

    @NotBlank(message = "회원의 닉네임을 입력해주세요.")
    @ApiModelProperty(notes = "닉네임을 입력해 주세요.")
    private String nickname;

    private String photo;


    public static User toEntity(UserJoinReq userJoinReq) {
        return User.builder()
                .loginId(userJoinReq.getId())
                .password(userJoinReq.getPassword())
                .nickName(userJoinReq.getNickname())
                .phone(userJoinReq.getPhone())
                .photo(userJoinReq.getPhoto())
                .signUpDate(LocalDateTime.now())
                .growth(1)
                .level(1).build();
    }
}
