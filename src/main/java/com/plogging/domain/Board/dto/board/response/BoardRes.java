package com.plogging.domain.Board.dto.board.response;

import com.plogging.domain.Board.entity.Board;
import com.plogging.domain.Board.entity.Photo;
import com.plogging.global.enumerations.PresenceStatus;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

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

    private boolean isFirstBoard;

    public static BoardRes create(Board board , boolean isFirstBoard, List<Photo> photos) {
        BoardRes boardRes = new BoardRes();
        boardRes.title = board.getTitle();
        boardRes.content = board.getContent();
        boardRes.time = board.getTime();
        boardRes.status = board.getStatus();
        boardRes.heartCnt = board.getHeartCnt();
        boardRes.commentCnt = board.getCommentCnt();
        boardRes.isFirstBoard = isFirstBoard;

        for (Photo i : photos){
            boardRes.photoUrls.add(i.getUrl());
        }

        return boardRes;
    }
}
