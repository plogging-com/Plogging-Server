package com.plogging.domain.User.controller;

import com.plogging.domain.User.dto.UserJoinReq;
import com.plogging.domain.User.dto.UserJoinRes;
import com.plogging.domain.User.dto.UserLoginReq;
import com.plogging.domain.User.dto.UserLoginRes;
import com.plogging.domain.User.service.UserService;
import com.plogging.global.dto.ApplicationResponse;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/users")
@Api(tags = "User API")
public class UserController {

    private final UserService userService;

    @PostMapping("/join")
    public ApplicationResponse<UserJoinRes> join(@Valid @RequestBody UserJoinReq userJoinReq){
        return ApplicationResponse.create(
                "가입완료",
                userService.join(userJoinReq)
        );
    }

    @PostMapping("/login")
    public ApplicationResponse<UserLoginRes> login(@Valid @RequestBody UserLoginReq userLoginReq){
        return ApplicationResponse.create(
                "로그인 성공",
                userService.login(userLoginReq)
        );
    }




}
