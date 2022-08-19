package com.plogging.domain.Board.controller;

import com.plogging.domain.Board.dto.board.request.createReportReq;
import com.plogging.domain.Board.dto.board.response.ReportRes;
import com.plogging.domain.Board.service.report.ReportServiceImpl;
import com.plogging.global.dto.ApplicationResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 전체 게시글 신고 현황 전체 조회
     * @author 강신현
     */
    @ApiOperation(value = "전체 게시글 신고 현황 조회", notes = "게시글 신고 상태는 처리중, 처리완료 둘 중 하나로 표시됩니다")
    @GetMapping("")
    public ApplicationResponse<Page<ReportRes>> findAllReports(Pageable pageable){
        return reportServiceImpl.findAllReports(pageable);
    }
}
