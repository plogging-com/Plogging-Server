package com.plogging.domain.Board.dto.board.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class getReCommentReq {
    private Long boardId;
    private Long commentId;
}
