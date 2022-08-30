package com.plogging.domain.User.repository;

import com.plogging.domain.User.entity.Badge;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BadgeRepository extends JpaRepository<Badge , Long> {

    Optional<Badge> findByName(String name);


}
