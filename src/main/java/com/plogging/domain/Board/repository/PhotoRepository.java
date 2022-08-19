package com.plogging.domain.Board.repository;

import com.plogging.domain.Board.entity.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoRepository extends JpaRepository<Photo, Long> {
}
