package com.plogging.domain.User.service;

import com.plogging.domain.User.entity.User;

public interface UserRefreshTokenService {

    long insertRefreshToken(String refreshJwt, User user);

}
