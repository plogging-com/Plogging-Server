package com.plogging.domain.User.service;

import com.plogging.domain.User.dto.*;
import com.plogging.domain.User.entity.User;
import com.plogging.domain.User.exception.NotFoundUserException;
import com.plogging.domain.User.exception.UserIdDuplicationException;
import com.plogging.domain.User.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserJoinRes join(UserJoinReq userJoinReq) {

        String name = userRepository.save(User.toEntity(userJoinReq)).getName();
        return UserJoinRes.builder().username(name).build();
    }

    @Override
    public User delete(UserDeleteReq userDeleteReq) {
        return null;
    }

    @Override
    public UserLoginRes login(UserLoginReq userLoginReq) {
        User user = userRepository.findByLoginId(userLoginReq.getLoginId()).orElseThrow(NotFoundUserException::new);
        return null;

    }


}