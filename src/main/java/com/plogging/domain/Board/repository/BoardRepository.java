package com.plogging.domain.Board.repository;

import com.plogging.domain.Board.dto.board.response.BoardListRes;
import com.plogging.domain.Board.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface BoardRepository extends JpaRepository<Board, Long> {

    Page<Board> findAll(Pageable pageable);

    @Query(value = "select new com.plogging.domain.Board.dto.board.response.BoardListRes(u.nickName, u.photo, b.title, b.content, b.time, b.photo)"
            + "from Board b join b.user u where b.status = 'ACTIVE'",
            countQuery = "select count(b) from Board b")
    Page<BoardListRes> findBoardDto(Pageable pageable);

}
