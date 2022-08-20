package com.plogging.domain.Board.dto.board.request;

import com.plogging.domain.Board.entity.Board;
import com.plogging.domain.Board.entity.Comment;
import com.plogging.domain.User.entity.User;
import com.plogging.global.enumerations.PresenceStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class createCommentReq {
    private Long userId;
    private Long boardId;

    private String content;
    private Long groupNum;

    public Comment toEntityComment(User user, Board board, Long groupNum){
        return Comment.builder()
                .user(user)
                .board(board)
                .content(this.getContent())
                .time(LocalDateTime.now())
                .groupNum(groupNum)
                .status(PresenceStatus.ACTIVE)
                .build();
    }

}
