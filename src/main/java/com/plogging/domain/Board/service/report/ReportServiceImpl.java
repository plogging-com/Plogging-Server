package com.plogging.domain.Board.service.report;

import com.plogging.domain.Board.dto.board.request.createReportReq;
import com.plogging.domain.Board.dto.board.request.editReportReq;
import com.plogging.domain.Board.dto.board.response.ReportRes;
import com.plogging.domain.Board.entity.Board;
import com.plogging.domain.Board.entity.Report;
import com.plogging.domain.Board.exception.Board.NotFoundBoardException;
import com.plogging.domain.Board.exception.Report.AlreadyStatusException;
import com.plogging.domain.Board.exception.Report.NotFoundReportException;
import com.plogging.domain.Board.exception.Report.ReportException;
import com.plogging.domain.Board.repository.BoardRepository;
import com.plogging.domain.Board.repository.ReportRepository;
import com.plogging.domain.User.entity.User;
import com.plogging.domain.User.exception.NotFoundUserException;
import com.plogging.domain.User.repository.UserRepository;
import com.plogging.global.dto.ApplicationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ReportServiceImpl implements ReportService{

    private final ReportRepository reportRepository;
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;

    @Transactional
    @Override
    public ApplicationResponse<ReportRes> createReport(createReportReq createReportReq){
        User user = userRepository.findById(createReportReq.getUserId()).orElseThrow(() -> new NotFoundUserException());
        Board board = boardRepository.findById(createReportReq.getBoardId()).orElseThrow(() -> new NotFoundBoardException());

        Report report = reportRepository.save(createReportReq.toEntityReport(user, board));

        ReportRes reportRes = ReportRes.create(report);

        return ApplicationResponse.create("create", reportRes);
    }

    @Override
    public ApplicationResponse<Page<ReportRes>> findAllReports(Pageable pageable){
        return ApplicationResponse.ok(reportRepository.findAll(pageable).map(ReportRes::create));
    }

    @Override
    public ApplicationResponse<ReportRes> editReportStatus(editReportReq editReportReq){
        Report report = reportRepository.findById(editReportReq.getReportId()).orElseThrow(() -> new NotFoundReportException());

        if(report.getStatus() == editReportReq.getStatus()) throw new AlreadyStatusException();

        return ApplicationResponse.ok(ReportRes.create(report.editStatus(editReportReq.getStatus())));
    }
}
