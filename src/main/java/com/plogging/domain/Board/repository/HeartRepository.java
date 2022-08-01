package com.plogging.domain.Board.repository;

import com.plogging.domain.Board.entity.Heart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HeartRepository extends JpaRepository<Heart, Long> {
    Heart heartCreate(Heart heart);
    Heart heartUpdate(Heart heart);
    void heartDeleteById(Long id);
}
