package com.plogging.domain.User.service;

import com.plogging.domain.User.dto.UserJoinReq;
import com.plogging.domain.User.dto.UserJoinRes;
import com.plogging.domain.User.entity.User;
import com.plogging.domain.User.exception.UserIdDuplicationException;
import com.plogging.domain.User.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Override
    public UserJoinRes join(UserJoinReq userJoinReq) {
        int i =0;
        if(i==0) throw new UserIdDuplicationException();


        return null;
    }
}
