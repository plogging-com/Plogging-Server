package com.plogging.domain.User.dto.request;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "유저 검색을 위한 객체")
public class BadgeRequest {

    private String comment;
    private String name;
    private MultipartFile multipartFile;

}
