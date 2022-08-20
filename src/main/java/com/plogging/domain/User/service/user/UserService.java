package com.plogging.domain.User.service.user;

import com.plogging.domain.User.dto.request.*;
import com.plogging.domain.User.dto.response.UserFindRes;
import com.plogging.domain.User.dto.response.UserJoinRes;
import com.plogging.domain.User.dto.response.UserLoginRes;
import com.plogging.domain.User.dto.response.UserUpdateFormRes;
import com.plogging.domain.User.entity.User;
import com.plogging.global.dto.ApplicationResponse;

import java.util.List;

public interface UserService {
    UserJoinRes join(UserJoinReq userJoinReq);
    ApplicationResponse<Void> delete(UserDeleteReq userDeleteReq);

    UserLoginRes login(UserLoginReq userLoginReq);

    String checNickname(String name);

    String checkLoginId(String loginId);

    List<UserFindRes> findUser(UserFindReq userFindReq);

    UserUpdateFormRes getUpdateForm(UserUpdateFormReq userUpdateFormReq);

    ApplicationResponse<Void> update(UserUpdateReq userUpdateReq);
}
