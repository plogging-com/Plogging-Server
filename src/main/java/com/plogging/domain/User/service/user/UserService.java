package com.plogging.domain.User.service.user;

import com.plogging.domain.User.dto.request.*;
import com.plogging.domain.User.dto.response.*;
import com.plogging.global.dto.ApplicationResponse;

import java.util.List;

public interface UserService {
    UserJoinRes join(UserJoinReq userJoinReq);
    ApplicationResponse<Void> delete(UserDeleteReq userDeleteReq);

    UserLoginRes login(UserLoginReq userLoginReq);

    String checkNickname(String name);

    String checkLoginId(String loginId);

    List<UserFindRes> findUser(UserFindReq userFindReq);

    UserUpdateFormRes getUpdateForm();

    ApplicationResponse<Void> update(UserUpdateReq userUpdateReq);

    UserHomeRes home();

    UserInfoRes info();

    String terms(String condition);

    String findId(String phoneNum);

    Boolean findPw(String loginId, String password);
}
