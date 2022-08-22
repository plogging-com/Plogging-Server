package com.plogging.domain.Board.dto.board.request;

import com.plogging.domain.Board.entity.Board;
import com.plogging.domain.Board.entity.Category;
import com.plogging.domain.Board.entity.CategoryName;
import com.plogging.domain.User.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class createBoardReq {

    private String title;
    private String content;
    //private MultipartFile photo;
    private Long userId;
    private CategoryName categoryName1;
    private CategoryName categoryName2;
    private CategoryName categoryName3;

    public Board toEntityBoardWithPhoto(User user) {
        return new Board(user, this.title, this.content, LocalDateTime.now());
    }

    public Category toEntityCategory(CategoryName categoryName){
        return Category.builder()
                .categoryName(categoryName)
                .build();
    }
}
