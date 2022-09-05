package com.plogging.domain.Board.dto.board.request;

import com.plogging.domain.Board.entity.CategoryName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class modifyBoardReq {
    private Long boardId;

    private String title;
    private String content;
    private List<MultipartFile> photos;
    private CategoryName categoryName1;
    private CategoryName categoryName2;
    private CategoryName categoryName3;
}
