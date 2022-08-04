package com.plogging.domain.User.service;

import com.plogging.domain.User.dto.request.UserDeleteReq;
import com.plogging.domain.User.dto.request.UserJoinReq;
import com.plogging.domain.User.dto.request.UserLoginReq;
import com.plogging.domain.User.dto.response.UserJoinRes;
import com.plogging.domain.User.dto.response.UserLoginRes;
import com.plogging.domain.User.entity.User;

public interface UserService {
    UserJoinRes join(UserJoinReq userJoinReq);
    User delete(UserDeleteReq userDeleteReq);

    UserLoginRes login(UserLoginReq userLoginReq);

    String checNickname(String name);

    String checkLoginId(String loginId);
}
