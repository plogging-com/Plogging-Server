package com.plogging.domain.Board.service.report;

import com.plogging.domain.Board.dto.board.request.createReportReq;
import com.plogging.domain.Board.dto.board.response.ReportRes;
import com.plogging.domain.Board.entity.Board;
import com.plogging.domain.Board.entity.Report;
import com.plogging.domain.Board.entity.ReportStatus;
import com.plogging.domain.Board.exception.Board.NotFoundBoardException;
import com.plogging.domain.Board.repository.BoardRepository;
import com.plogging.domain.Board.repository.ReportRepository;
import com.plogging.domain.User.entity.User;
import com.plogging.domain.User.exception.NotFoundUserException;
import com.plogging.domain.User.repository.UserRepository;
import com.plogging.global.dto.ApplicationResponse;
import lombok.RequiredArgsConstructor;
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
}
