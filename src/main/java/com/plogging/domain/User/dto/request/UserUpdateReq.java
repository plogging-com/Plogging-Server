package com.plogging.domain.User.dto.request;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("유저정보 수정 요청을 위한 객체")
public class UserUpdateReq {

    private String nickname;
    private MultipartFile photo;

}
