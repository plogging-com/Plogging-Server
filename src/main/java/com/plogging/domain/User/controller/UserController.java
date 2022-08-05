package com.plogging.domain.User.controller;

import com.plogging.domain.User.dto.request.UserJoinReq;
import com.plogging.domain.User.dto.response.UserJoinRes;
import com.plogging.domain.User.dto.request.UserLoginReq;
import com.plogging.domain.User.dto.response.UserLoginRes;
import com.plogging.domain.User.service.UserService;
import com.plogging.global.dto.ApiErrorResponse;
import com.plogging.global.dto.ApplicationResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/users")
@Api(tags = "User API")
public class UserController {

    private final UserService userService;

    /**
     * 회원가입
     * @param userJoinReq
     * @return UserJoinRes
     * @author 한규범
     */
    @PostMapping("/join")
    @ApiOperation(value = "사용자 회원가입", notes = "회원가입 진햅합니다.")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "(V0001) \n 로그인 Id는 6~18글자의 영소문자, 숫자만 가능합니다. \n 비밀번호는 특수문자를 포함한 8~20글자의 영대/소문자, 숫자만 가능합니다. \n 닉네임은 2~5글자의 영소문자, 숫자, 한글만 가능합니다.", response = ApiErrorResponse.class)

    })
    public ApplicationResponse<UserJoinRes> join(@Valid @RequestBody UserJoinReq userJoinReq){
        return ApplicationResponse.create(
                "가입완료",
                userService.join(userJoinReq)
        );
    }

    @PostMapping("/login")
    @ApiOperation(value = "사용자 로그인", notes = "로그인을 진행합니다.")
    public ApplicationResponse<UserLoginRes> login(@Valid @RequestBody UserLoginReq userLoginReq){
        return ApplicationResponse.create(
                "로그인 성공",
                userService.login(userLoginReq)
        );
    }

    @PostMapping("/check-nickname")
    @ApiOperation(value = "사용자 닉네임 중복 체크", notes = "회원가입에서 닉네임에서 다른 쪽으로 넘어갈 때 호출합니다.")
    public ApplicationResponse<String> checkNickename(String name){
        return ApplicationResponse.ok(userService.checNickname(name));
    }

    @PostMapping("/check-id")
    @ApiOperation(value = "사용자 닉네임 중복 체크", notes = "회원가입에서 닉네임에서 다른 쪽으로 넘어갈 때 호출합니다.")
    public ApplicationResponse<String> checkLoginId(String loginId){
        return ApplicationResponse.ok(userService.checkLoginId(loginId));
    }


}
