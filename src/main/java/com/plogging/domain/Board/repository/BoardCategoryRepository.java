package com.plogging.domain.Board.repository;

import com.plogging.domain.Board.entity.BoardCategory;
import com.plogging.domain.Board.entity.Category;
import org.apache.el.stream.Stream;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BoardCategoryRepository extends JpaRepository<BoardCategory, Long> {
    Page<BoardCategory> findByCategory(Pageable pageable, Category category);

    @Query(value = "select distinct (board.board_idx), comment_cnt, content, heart_cnt, status, time, title, user_idx, main_photo_url " +
            " from  board, board_category, category" +
            " where board.board_idx = board_category.board_idx" +
            "        and board_category.category_idx = category.category_idx" +
            "        and category.name IN (:param1)", nativeQuery = true)
    Page<BoardCategory> findAllByCategoryIn(Pageable pageable, List<Category> categories, String param1);


}
