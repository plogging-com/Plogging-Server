package com.plogging.domain.User.service;

import com.plogging.domain.User.dto.*;
import com.plogging.domain.User.entity.User;

public interface UserService {
    UserJoinRes join(UserJoinReq userJoinReq);
    User delete(UserDeleteReq userDeleteReq);

    UserLoginRes login(UserLoginReq userLoginReq);

    String checNickname(String name);

    String checkLoginId(String loginId);
}
