package com.plogging.domain.User.controller;

import com.plogging.domain.User.dto.request.*;
import com.plogging.domain.User.dto.response.*;
import com.plogging.domain.User.service.user.UserService;
import com.plogging.global.dto.ApiErrorResponse;
import com.plogging.global.dto.ApplicationErrorResponse;
import com.plogging.global.dto.ApplicationResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/users")
@Api(tags = "User API")
public class UserController {

    private final UserService userService;

    /**
     * 회원가입
     * @author 한규범
     */
    @PostMapping("/join")
    @ApiOperation(value = "사용자 회원가입", notes = "회원가입 진햅합니다.")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "(V0001) \n 로그인 Id는 6~18글자의 영소문자, 숫자만 가능합니다. \n 비밀번호는 특수문자를 포함한 8~20글자의 영대/소문자, 숫자만 가능합니다. \n 닉네임은 2~5글자의 영소문자, 숫자, 한글만 가능합니다.", response = ApiErrorResponse.class),
            @ApiResponse(code = 400, message = "(U0008) \n 해당 번호로 이미 가입되어 있습니다.", response = ApiErrorResponse.class)


    })
    public ApplicationResponse<UserJoinRes> join(@Valid @RequestBody UserJoinReq userJoinReq){
        return ApplicationResponse.create(
                "가입완료",
                userService.join(userJoinReq)
        );
    }

    /**
     * 로그인
     * @param userLoginReq
     * @return
     * author 신동민
     */
    @PostMapping("/login")
    @ApiOperation(value = "사용자 로그인", notes = "로그인을 진행합니다.")
    public ApplicationResponse<UserLoginRes> login(@Valid @RequestBody UserLoginReq userLoginReq){
        return ApplicationResponse.create(
                "로그인 성공",
                userService.login(userLoginReq)
        );
    }

    @PostMapping("/delete")
    @ApiOperation(value = "사용자 탈퇴", notes = "탈퇴를 진행합니다.")
    public ApplicationResponse<Void> login(@Valid @RequestBody UserDeleteReq userDeleteReq){
        return userService.delete(userDeleteReq);
    }


    /**
     * 사용자 닉네임으로 찾기 (유사한 닉네임은
     * @param userFindReq
     * @return
     */

    @ApiOperation(value = "사용자 찾기" , notes = "사용자 페이지를 검색합니다.")
    @PostMapping("/findUserContaining")
    public ApplicationResponse<List<UserFindRes>> findUserContaining(@Valid @RequestBody UserFindReq userFindReq){
        return ApplicationResponse.create("검색한 사용자에 대한 정보입니다." , userService.findUser(userFindReq));
    }

    @ApiOperation(value = "사용자 정보 수정폼 불러오기" , notes = "사용자의 정보를 수정하기 위한 폼을 불러옵니다.")
    @GetMapping("/updateForm")
    public ApplicationResponse<UserUpdateFormRes> getUserUpdateForm(){
        return ApplicationResponse.create("검색한 사용자에 대한 정보입니다." , userService.getUpdateForm());
    }


    @ApiOperation(value = "사용자 정보 수정하기" , notes = "사용자의 정보를 수정합니다.")
    @PostMapping("/update")
    public ApplicationResponse<Void> updateUser(@Valid UserUpdateReq userUpdateReq){
        return userService.update(userUpdateReq);
    }

    /**
     * 사용자 닉네임 중복 체크
     * @author 한규범
     */
    @PostMapping("/check-nickname")
    @ApiResponses(value = {
            @ApiResponse(code = 409, message = "(U0002) 이미 존재하는 닉네임 입니다. \n (U0004) 닉네임은 2~5글자의 영소문자, 숫자, 한글만 가능합니다.", response = ApplicationErrorResponse.class)
    })
    @ApiOperation(value = "사용자 닉네임 중복 체크", notes = "회원가입에서 닉네임에서 다른 쪽으로 넘어갈 때 호출합니다.")
    public ApplicationResponse<String> checkNickename(String name){
        return ApplicationResponse.ok(userService.checkNickname(name));
    }

    /**
     * 사용자 아이디 중복 체크
     * @author 한규범
     */
    @PostMapping("/check-id")
    @ApiOperation(value = "사용자 아이디 중복 체크", notes = "회원가입에서 닉네임에서 다른 쪽으로 넘어갈 때 호출합니다.")
    @ApiResponses(value = {
            @ApiResponse(code = 409, message = "(U0001) 이미 존재하는 아이디입니다. \n (U0003) 로그인 Id는 5~12글자의 영소문자, 숫자만 가능합니다.", response = ApplicationErrorResponse.class)
    })
    public ApplicationResponse<String> checkLoginId(String loginId){
        return ApplicationResponse.ok(userService.checkLoginId(loginId));
    }

    /**
     * 사용자 홈 화면
     * @author 한규범
     */
    @GetMapping("/home")
    @ApiOperation(value = "사용자 홈 화면", notes = "사용자의 홈 화면을 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "토큰 미입력 경우: 토큰을 찾을수 없습니다.", response = ApiErrorResponse.class),
            @ApiResponse(code = 404, message = "토큰 만료 경우: 토큰이 만료 되었습니다.", response = ApiErrorResponse.class)
    })
    public ApplicationResponse<UserHomeRes> home(){
        return ApplicationResponse.ok(userService.home());
    }


    @GetMapping("/info")
    @ApiOperation(value = "사용자 정보 화면", notes = "사용자의 정보를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "토큰 미입력 경우: 토큰을 찾을수 없습니다.", response = ApiErrorResponse.class),
            @ApiResponse(code = 404, message = "토큰 만료 경우: 토큰이 만료 되었습니다.", response = ApiErrorResponse.class)
    })
    public ApplicationResponse<UserInfoRes> info(){
        return ApplicationResponse.ok(userService.info());
    }


    @GetMapping("/terms")
    @ApiOperation(value = "서비스 이용약관, 개인정보 처리 방침 조회 \n condition = service(이용약관), privacy(개인정보 처리 방침)")
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "조건 값이 올바르지 않습니다.", response = ApiErrorResponse.class)
    })
    public ApplicationResponse<String> terms(String condition){
        return ApplicationResponse.ok(userService.terms(condition));
    }

    /**
     * 유저 아이디 찾기 기능
     * @author 한규범
     */
    @PostMapping("/find-id")
    @ApiOperation(value = "유저 아이디 찾기")
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "해당 유저를 찾을 수 없습니다.", response = ApiErrorResponse.class)
    })
    public ApplicationResponse<String> findId(String phoneNum){
        return ApplicationResponse.ok(userService.findId(phoneNum));
    }

    /**
     * 유저 비밀번호 찾기 기능
     * @author 한규범
     */
    @PostMapping("/find-pw")
    @ApiOperation(value = "유저 비밀번호 찾기(변경)")
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "해당 유저를 찾을 수 없습니다.", response = ApiErrorResponse.class)
    })
    public ApplicationResponse<Boolean> findPw(String loginId, String password){
        return ApplicationResponse.ok(userService.findPw(loginId,password));
    }


}
