package com.plogging.domain.Board.dto.board.request;

import com.plogging.domain.Board.entity.ReportStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class editReportReq {
    private Long reportId;
    private ReportStatus status;
}
