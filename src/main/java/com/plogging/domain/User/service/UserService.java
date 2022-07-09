package com.plogging.domain.User.service;

import com.plogging.domain.User.dto.UserDeleteReq;
import com.plogging.domain.User.dto.UserJoinReq;
import com.plogging.domain.User.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

public interface UserService {
    User join(UserJoinReq userJoinReq);

    User delete(UserDeleteReq userDeleteReq);
}
