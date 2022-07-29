package com.plogging.domain.User.service;

import com.plogging.domain.User.dto.UserJoinReq;
import com.plogging.domain.User.dto.UserJoinRes;
import com.plogging.global.dto.ApplicationResponse;

public interface UserService {

    UserJoinRes join(UserJoinReq userJoinReq);
}
