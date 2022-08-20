package com.plogging.domain.Board.repository;

import com.plogging.domain.Board.entity.Comment;
import org.apache.el.stream.Stream;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.*;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    Slice<Comment> findAllByBoardIdAndGroupNum(Pageable pageable, Long boardId, Long commentId);
}
