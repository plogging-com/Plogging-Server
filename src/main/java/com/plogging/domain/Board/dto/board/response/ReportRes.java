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
    private String content;
    private LocalDateTime time;
    private ReportStatus status;

    public static ReportRes create(Report report){
        ReportRes reportRes = new ReportRes();
        reportRes.content = report.getContent();
        reportRes.time = report.getTime();
        reportRes.status = report.getStatus();

        return reportRes;
    }
}
