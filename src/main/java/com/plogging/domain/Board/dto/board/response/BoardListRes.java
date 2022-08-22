package com.plogging.domain.Board.dto.board.response;

import com.plogging.domain.Board.entity.Board;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BoardListRes {
    private String nickName;
    private String userPhoto;

    private Long boardId;
    private String title;
    private String content;
    private LocalDateTime time;
    private String photo;

    private int heartCnt;
    private int commentCnt;

    private List<BoardCategoryRes> boardCategories;

    public BoardListRes(Board board) {
        this.nickName = board.getUser().getNickName();
        this.userPhoto = board.getUser().getPhoto();

        this.boardId = board.getId();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.time = board.getTime();
        this.photo = "~~~"; // TODO
        this.heartCnt = board.getHeartCnt();
        this.commentCnt = board.getCommentCnt();

        this.boardCategories = board.getBoardCategories().stream()
                .map(boardCategory -> new BoardCategoryRes(boardCategory))
                .collect(Collectors.toList());
    }

    public static BoardListRes create(Board board){
        BoardListRes boardListRes = new BoardListRes();

        boardListRes.nickName = board.getUser().getNickName();
        boardListRes.userPhoto = board.getUser().getPhoto();

        boardListRes.title = board.getTitle();
        boardListRes.content = board.getContent();
        boardListRes.time = board.getTime();

        boardListRes.heartCnt = board.getHeartCnt();
        boardListRes.commentCnt = board.getCommentCnt();

        return boardListRes;
    }
}
