package com.plogging.domain.Board.repository;

import com.plogging.domain.Board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {

    Board boardCreate(Board board);
    Board boardUpdate(Board board);
    void boardDeleteById(Board board);
}
