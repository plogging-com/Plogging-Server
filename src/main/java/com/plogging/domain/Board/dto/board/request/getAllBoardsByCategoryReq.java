package com.plogging.domain.Board.dto.board.request;

import com.plogging.domain.Board.dto.board.response.BoardCategoryRes;
import com.plogging.domain.Board.entity.CategoryName;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class getAllBoardsByCategoryReq {
    private List<String> categoryName;
}
