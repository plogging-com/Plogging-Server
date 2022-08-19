package com.plogging.domain.Board.service.report;

import com.plogging.domain.Board.dto.board.request.createReportReq;
import com.plogging.domain.Board.dto.board.request.editReportReq;
import com.plogging.domain.Board.dto.board.response.ReportRes;
import com.plogging.global.dto.ApplicationResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReportService {
    ApplicationResponse<ReportRes> createReport(createReportReq createReportReq);

    ApplicationResponse<Page<ReportRes>> findAllReports(Pageable pageable);

    ApplicationResponse<ReportRes> editReportStatus(editReportReq editReportReq);
}
