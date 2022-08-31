package com.plogging.domain.Board.repository;

import com.plogging.domain.Board.entity.BoardCategory;
import com.plogging.domain.Board.entity.Category;
import org.apache.el.stream.Stream;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardCategoryRepository extends JpaRepository<BoardCategory, Long> {
}
