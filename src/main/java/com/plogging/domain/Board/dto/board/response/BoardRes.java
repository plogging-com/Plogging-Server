package com.plogging.domain.Board.dto.board.response;

import com.plogging.domain.Board.entity.Board;
import com.plogging.global.enumerations.PresenceStatus;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

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

    public static BoardRes create(Board board) {
        BoardRes boardRes = new BoardRes();
        boardRes.title = board.getTitle();
        boardRes.content = board.getContent();
        boardRes.time = board.getTime();
        boardRes.status = board.getStatus();
        boardRes.heartCnt = board.getHeartCnt();
        boardRes.commentCnt = board.getCommentCnt();

        return boardRes;
    }
}
