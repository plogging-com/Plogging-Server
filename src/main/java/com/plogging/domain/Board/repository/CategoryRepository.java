package com.plogging.domain.Board.repository;


import com.plogging.domain.Board.entity.Category;
import com.plogging.domain.Board.entity.CategoryName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByName(CategoryName categoryName);
}
