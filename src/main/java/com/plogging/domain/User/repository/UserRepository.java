package com.plogging.domain.User.repository;

import com.plogging.domain.User.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>{


    Optional<User> findByLoginId(String loginId);
}
