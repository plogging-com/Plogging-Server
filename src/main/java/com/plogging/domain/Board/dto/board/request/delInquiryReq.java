package com.plogging.domain.Board.dto.board.request;

import com.plogging.domain.Board.entity.Board;
import com.plogging.domain.Board.entity.Inquiry;
import com.plogging.domain.User.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class delInquiryReq {

    private Long boardId;
    private Long userId;

    public Inquiry toEntity(User user, Board board){
        return Inquiry.builder()
                .user(user)
                .board(board)
                .build();
    }
}
