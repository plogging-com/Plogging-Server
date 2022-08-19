package com.plogging.domain.Board.controller;

import com.plogging.domain.Board.dto.board.request.createReportReq;
import com.plogging.domain.Board.dto.board.response.ReportRes;
import com.plogging.domain.Board.service.report.ReportServiceImpl;
import com.plogging.global.dto.ApplicationResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@Api(tags = "Report API")
@RequestMapping("api/report")
public class ReportController {

    private final ReportServiceImpl reportServiceImpl;

    /**
     * 게시글 신고
     * @author 강신현
     */
    @ApiOperation(value = "게시글 신고", notes = "게시글 신고 직후, 신고건은 처리중으로 표시됩니다.")
    @PostMapping("")
    public ApplicationResponse<ReportRes> createReport(@ModelAttribute createReportReq createReportReq){
        return reportServiceImpl.createReport(createReportReq);
    }
}
