package com.plogging.domain.User.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.NonNull;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class UserJoinReq {

    @NotBlank
    private String name;

}
