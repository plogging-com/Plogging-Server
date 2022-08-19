package com.plogging.domain.Board.dto.board.response;

import com.plogging.domain.Board.entity.Report;
import com.plogging.domain.Board.entity.ReportStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReportRes {
    private Long reportId;
    private Long userId;
    private Long boardId;
    private String content;
    private LocalDateTime time;
    private ReportStatus status;

    public static ReportRes create(Report report){
        ReportRes reportRes = new ReportRes();
        reportRes.reportId = report.getId();
        reportRes.userId = report.getUser().getId();
        reportRes.boardId = report.getBoard().getId();
        reportRes.content = report.getContent();
        reportRes.time = report.getTime();
        reportRes.status = report.getStatus();

        return reportRes;
    }
}
