package com.plogging.domain.Board.dto.board.response;

import com.plogging.domain.Board.entity.Board;
import com.plogging.domain.Board.entity.Photo;
import com.plogging.domain.Board.entity.CategoryName;
import com.plogging.global.enumerations.PresenceStatus;
import com.plogging.global.utill.imgae.AwsS3Service;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

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
    private PresenceStatus status;
    private int heartCnt;
    private int commentCnt;
    private List<String> photoUrls = new ArrayList<>();

    private List<CategoryName> categories = new ArrayList<>();

    private boolean isFirstBoard;

    public static BoardRes create(Board board , boolean isFirstBoard, List<String> urls) {
        BoardRes boardRes = new BoardRes();
        boardRes.title = board.getTitle();
        boardRes.content = board.getContent();
        boardRes.time = board.getTime();
        boardRes.status = board.getStatus();
        boardRes.heartCnt = board.getHeartCnt();
        boardRes.commentCnt = board.getCommentCnt();
        boardRes.isFirstBoard = isFirstBoard;
        boardRes.photoUrls = urls;

        return boardRes;
    }

    public void addCategory(CategoryName category){
        this.categories.add(category);
    }
}
