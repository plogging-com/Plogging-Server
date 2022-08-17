package com.plogging.domain.Board.dto.board.response;

import com.plogging.domain.Board.entity.Board;
import com.plogging.domain.Board.entity.CategoryName;
import com.plogging.global.enumerations.PresenceStatus;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BoardRes {
    private String title;
    private String content;
    private LocalDateTime time;
    private String photo;
    private PresenceStatus status;

    private List<CategoryName> categories = new ArrayList<>();

    public static BoardRes create(Board board) {
        BoardRes boardRes = new BoardRes();
        boardRes.title = board.getTitle();
        boardRes.content = board.getContent();
        boardRes.time = board.getTime();
        boardRes.photo = board.getPhoto();
        boardRes.status = board.getStatus();

        return boardRes;
    }

    public void addCategory(CategoryName category){
        this.categories.add(category);
    }
}
