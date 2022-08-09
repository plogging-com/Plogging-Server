package com.plogging.domain.Board.dto.board.response;

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

//    private long heartCnt;
//    private long commentCnt;
}
