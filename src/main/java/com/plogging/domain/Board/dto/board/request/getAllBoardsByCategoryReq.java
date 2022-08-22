package com.plogging.domain.Board.dto.board.request;

import com.plogging.domain.Board.dto.board.response.BoardCategoryRes;
import com.plogging.domain.Board.entity.CategoryName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class getAllBoardsByCategoryReq {
    private CategoryName categoryName1;
    private CategoryName categoryName2;
    private CategoryName categoryName3;
}
