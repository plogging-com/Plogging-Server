package com.plogging.domain.User.dto;

import io.swagger.annotations.ApiModel;
import lombok.*;
import org.springframework.lang.NonNull;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "회원가입 반환 객체")
public class UserJoinReq {

    @NotBlank
    private String loginId;
    @NotBlank
    private String password;
    @NotBlank
    private String name;
    @NotBlank
    private String nickName;
    @NotBlank
    private String phone;
    @NotBlank
    private String photo;
    @NotBlank
    private String growth;

}
