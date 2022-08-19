package com.plogging.domain.Board.dto.board.response;

import com.plogging.domain.Board.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommentRes {
    private Long userId;
    private Long boardId;

    private String content;
    private LocalDateTime time;
    private Long groupNum;

    public static CommentRes create(Comment comment) {
        CommentRes commentRes = new CommentRes();
        commentRes.userId = comment.getUser().getId();
        commentRes.boardId = comment.getBoard().getId();
        commentRes.content = comment.getContent();
        commentRes.time = comment.getTime();
        commentRes.groupNum = comment.getGroupNum();

        return commentRes;
    }
}
