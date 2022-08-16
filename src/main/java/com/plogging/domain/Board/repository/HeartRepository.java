package com.plogging.domain.Board.repository;

import com.plogging.domain.Board.entity.Board;
import com.plogging.domain.Board.entity.Heart;
import com.plogging.domain.User.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HeartRepository extends JpaRepository<Heart, Long> {
    Optional<Heart> findByUserAndBoard(User user, Board board);
}
