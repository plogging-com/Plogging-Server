package com.plogging.domain.Board.repository;

import com.plogging.domain.Board.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
