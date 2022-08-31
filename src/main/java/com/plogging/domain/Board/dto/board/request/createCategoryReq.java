package com.plogging.domain.Board.dto.board.request;

import com.plogging.domain.Board.entity.CategoryName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class createCategoryReq {
    private CategoryName categoryName;
}
