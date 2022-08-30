package com.plogging.domain.Board.controller;

import com.plogging.domain.Board.dto.board.request.createCategoryReq;
import com.plogging.domain.Board.entity.CategoryName;
import com.plogging.domain.Board.service.Category.CategoryService;
import com.plogging.global.dto.ApplicationResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@Api(tags = "Category API")
@RequestMapping("api/category")
public class CategoryController {

    private final CategoryService categoryService;

    /**
     * 카테고리 등록
     * @author 강신현
     */
    @ApiOperation(value = "카테고리 등록", notes = "")
    @PostMapping("")
    public ApplicationResponse<Void> categoryCreate(@ModelAttribute createCategoryReq createCategoryReq){
        return categoryService.createCategory(createCategoryReq.getCategoryName());
    }
}
