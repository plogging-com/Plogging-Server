package com.plogging.domain.User.service;

import com.plogging.domain.User.dto.*;
import com.plogging.domain.User.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

public interface UserService {
    UserJoinRes join(UserJoinReq userJoinReq);
    User delete(UserDeleteReq userDeleteReq);

    UserLoginRes login(UserLoginReq userLoginReq);

}
