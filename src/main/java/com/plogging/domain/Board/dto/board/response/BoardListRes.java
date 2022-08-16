package com.plogging.domain.Board.dto.board.response;

import com.plogging.domain.Board.entity.Board;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BoardListRes {
    private String nickName;
    private String userPhoto;
    // private String userBadgePhoto;

    private String title;
    private String content;
    private LocalDateTime time;
    private String photo;

    private int heartCnt;
    private int commentCnt;

    public static BoardListRes create(Board board){
        BoardListRes boardListRes = new BoardListRes();

        boardListRes.nickName = board.getUser().getNickName();
        boardListRes.userPhoto = board.getUser().getPhoto();

        boardListRes.title = board.getTitle();
        boardListRes.content = board.getContent();
        boardListRes.time = board.getTime();
        boardListRes.photo = board.getPhoto();

        boardListRes.heartCnt = board.getHeartCnt();
        boardListRes.commentCnt = board.getCommentCnt();

        return boardListRes;
    }
}
