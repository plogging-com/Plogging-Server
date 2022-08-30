package com.plogging.domain.Board.dto.board.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class modifyCommentReq {
    private Long commentId;
    private String newContent;
}
