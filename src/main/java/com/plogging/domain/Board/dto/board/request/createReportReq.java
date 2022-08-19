package com.plogging.domain.Board.dto.board.request;

import com.plogging.domain.Board.entity.Board;
import com.plogging.domain.Board.entity.Report;
import com.plogging.domain.Board.entity.ReportStatus;
import com.plogging.domain.User.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class createReportReq {
    private Long userId;
    private Long boardId;

    private String content;

    public Report toEntityReport(User user, Board board) {
        return Report.builder()
                .user(user)
                .board(board)
                .content(this.getContent())
                .time(LocalDateTime.now())
                .status(ReportStatus.처리중)
                .build();
    }
}
