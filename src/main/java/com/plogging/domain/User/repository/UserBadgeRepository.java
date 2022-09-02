package com.plogging.domain.User.repository;

import com.plogging.domain.User.entity.Badge;
import com.plogging.domain.User.entity.User;
import com.plogging.domain.User.entity.UserBadge;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserBadgeRepository extends JpaRepository<UserBadge , Long> {

    Optional<UserBadge> findByUserAndBadge(User user , Badge badge);

    Optional<UserBadge> findByUserAndIsMainBadge(User user, boolean isMainBadge);

    List<UserBadge> findByUser(User user);



}
