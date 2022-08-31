package com.plogging.domain.Board.repository;

import com.plogging.domain.Board.entity.Board;
import com.plogging.domain.User.entity.User;
import com.plogging.domain.Board.entity.BoardCategory;
import com.plogging.domain.Board.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface BoardRepository extends JpaRepository<Board, Long> {

    Page<Board> findAll(Pageable pageable);

    Optional<List<Board>> findByUser(User user);

    @Query(value = "select distinct (board.board_idx), comment_cnt, content, heart_cnt, status, time, title, user_idx, main_photo_url " +
            "from  board, board_category, category " +
            "where board.board_idx = board_category.board_idx " +
            "and board_category.category_idx = category.category_idx " +
            "and category.name IN (:categories)", nativeQuery = true)
    Page<Board> findAllByCategoryIn(Pageable pageable, @Param("categories") List<String> categories);

}
