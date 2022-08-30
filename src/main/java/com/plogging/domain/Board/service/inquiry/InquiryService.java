package com.plogging.domain.Board.service.inquiry;

import com.plogging.domain.Board.dto.board.request.delInquiryReq;
import com.plogging.global.dto.ApplicationResponse;

public interface InquiryService {
    void createInquiry(Long boardId);

    ApplicationResponse<Void> delInquiry(delInquiryReq delInquiryReq);
}
