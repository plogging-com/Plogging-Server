package com.plogging.domain.User.service;

import com.plogging.domain.User.dto.UserDeleteReq;
import com.plogging.domain.User.dto.UserJoinReq;
import com.plogging.domain.User.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService{


    @Override
    public User join(UserJoinReq userJoinReq) {
        return null;
    }

    @Override
    public User delete(UserDeleteReq userDeleteReq) {
        return null;
    }
}
