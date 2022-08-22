package com.plogging.domain.Board.repository;

import com.plogging.domain.Board.entity.Board;
import com.plogging.domain.Board.entity.BoardCategory;
import com.plogging.domain.Board.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface BoardRepository extends JpaRepository<Board, Long> {

    Page<Board> findAll(Pageable pageable);
}
