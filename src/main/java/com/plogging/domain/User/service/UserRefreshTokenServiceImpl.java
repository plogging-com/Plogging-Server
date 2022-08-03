package com.plogging.domain.User.service;

import com.plogging.domain.User.entity.User;
import com.plogging.domain.User.entity.UserRefreshToken;
import com.plogging.domain.User.repository.UserRefreshTokenRepository;
import com.plogging.domain.User.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
    public class UserRefreshTokenServiceImpl implements UserRefreshTokenService{
        private final UserRefreshTokenRepository userRefreshTokenRepository;

    @Override
    @Transactional
    public long insertRefreshToken(String refreshJwt, User user) {
        UserRefreshToken userRefreshToken = userRefreshTokenRepository.findByUser(user);
        if (userRefreshToken == null) {
            UserRefreshToken userRefreshTokenInsert = new UserRefreshToken();
            userRefreshTokenInsert.setUser(user);
            userRefreshTokenInsert.setUserRefreshToken(refreshJwt);
            userRefreshTokenRepository.save(userRefreshTokenInsert);
            return userRefreshTokenInsert.getUserRefreshTokenIdx();
        } else if (userRefreshToken != null) {
            userRefreshToken.setUserRefreshToken(refreshJwt);
        }
        return userRefreshToken.getUserRefreshTokenIdx();
    }

}
