package com.plogging.domain.Board.repository;

import com.plogging.domain.Board.entity.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhotoRepository extends JpaRepository<Photo, Long> {
    List<Photo> findAllByBoardId(Long boardId);
    Photo findFirstByBoardId(Long boardId);
}
