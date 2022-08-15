package com.plogging.domain.Board.dto.board.request;

import com.plogging.domain.Board.entity.Board;
import com.plogging.domain.Board.entity.Heart;
import com.plogging.domain.User.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class createHeartReq {

    private Long board_idx;

    public Heart toEntity(User user, Board board){
        return Heart.builder()
                .user(user)
                .board(board)
                .build();
    }
}
