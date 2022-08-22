package com.plogging.domain.Board.dto.board.response;

import com.plogging.domain.Board.entity.Board;
import com.plogging.domain.Board.entity.BoardCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BoardAllRes {
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

    public static BoardAllRes create(Board board){
        BoardAllRes boardAllRes = new BoardAllRes();

        boardAllRes.nickName = board.getUser().getNickName();
        boardAllRes.userPhoto = board.getUser().getPhoto();

        boardAllRes.boardId = board.getId();
        boardAllRes.title = board.getTitle();
        boardAllRes.content = board.getContent();
        boardAllRes.time = board.getTime();
        boardAllRes.photo = "~~~"; // TODO
        boardAllRes.heartCnt = board.getHeartCnt();
        boardAllRes.commentCnt = board.getCommentCnt();

        boardAllRes.boardCategories = board.getBoardCategories().stream()
                .map(boardCategory -> new BoardCategoryRes(boardCategory))
                .collect(Collectors.toList());

        return boardAllRes;
    }

    public static BoardAllRes create2(BoardCategory boardCategory){
        BoardAllRes boardAllRes = new BoardAllRes();

        boardAllRes.nickName = boardCategory.getBoard().getUser().getNickName();
        boardAllRes.userPhoto = boardCategory.getBoard().getUser().getPhoto();

        boardAllRes.boardId = boardCategory.getBoard().getId();
        boardAllRes.title = boardCategory.getBoard().getTitle();
        boardAllRes.content = boardCategory.getBoard().getContent();
        boardAllRes.time = boardCategory.getBoard().getTime();
        boardAllRes.photo = "~~~"; // TODO
        boardAllRes.heartCnt = boardCategory.getBoard().getHeartCnt();
        boardAllRes.commentCnt = boardCategory.getBoard().getCommentCnt();

        boardAllRes.boardCategories = boardCategory.getBoard().getBoardCategories().stream()
                .map(boardCategoryRepository -> new BoardCategoryRes(boardCategoryRepository))
                .collect(Collectors.toList());

        return boardAllRes;
    }
}
