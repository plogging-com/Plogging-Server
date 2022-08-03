package com.plogging.domain.User.repository;

import com.plogging.domain.User.entity.User;
import com.plogging.domain.User.entity.UserRefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRefreshTokenRepository extends JpaRepository<UserRefreshToken , Long> {

    UserRefreshToken findByRefreshToken(String refreshToken);

    UserRefreshToken findByUser(User user);

    UserRefreshToken findByUserRefreshTokenIdx(Long UserRefreshTokenIdx);

}
