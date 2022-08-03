package com.plogging.domain.User.service;

import com.plogging.domain.User.entity.User;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

public interface UserRefreshTokenService {

    long insertRefreshToken(String refreshJwt, User user);

}
