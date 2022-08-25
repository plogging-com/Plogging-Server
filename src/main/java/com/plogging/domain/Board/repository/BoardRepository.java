package com.plogging.domain.Board.repository;

import com.plogging.domain.Board.entity.Board;
import com.plogging.domain.User.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface BoardRepository extends JpaRepository<Board, Long> {

    Page<Board> findAll(Pageable pageable);

    Optional<List<Board>> findByUser(User user);

}
