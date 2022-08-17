package com.plogging.domain.Board.repository;


import com.plogging.domain.Board.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
