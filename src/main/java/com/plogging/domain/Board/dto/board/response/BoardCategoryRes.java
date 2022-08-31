package com.plogging.domain.Board.dto.board.response;

import com.plogging.domain.Board.entity.BoardCategory;
import com.plogging.domain.Board.entity.CategoryName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BoardCategoryRes {
    private CategoryName name;

    public BoardCategoryRes(BoardCategory boardCategory) {
        this.name = boardCategory.getCategory().getName();
    }
}

