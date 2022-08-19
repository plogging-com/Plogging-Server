package com.plogging.domain.Board.service.report;

import com.plogging.domain.Board.dto.board.request.createReportReq;
import com.plogging.domain.Board.dto.board.response.ReportRes;
import com.plogging.global.dto.ApplicationResponse;

public interface ReportService {
    ApplicationResponse<ReportRes> createReport(createReportReq createReportReq);
}
