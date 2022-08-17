package com.plogging.domain.Board.service.Category;

import com.plogging.domain.Board.entity.Category;
import com.plogging.domain.Board.entity.CategoryName;
import com.plogging.domain.Board.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Transactional
    @Override
    public void createCategory(CategoryName categoryName){
        Category category = Category.builder()
                .categoryName(categoryName)
                .build();

        categoryRepository.save(category);
    }
}
