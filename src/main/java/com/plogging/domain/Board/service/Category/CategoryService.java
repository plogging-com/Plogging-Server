package com.plogging.domain.Board.service.Category;

import com.plogging.domain.Board.entity.CategoryName;
import com.plogging.global.dto.ApplicationResponse;

public interface CategoryService {
    ApplicationResponse<Void> createCategory(CategoryName categoryName);
}