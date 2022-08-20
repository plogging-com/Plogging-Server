package com.plogging.domain.Board.controller;

import com.plogging.domain.Board.dto.board.request.delHeartReq;
import com.plogging.domain.Board.dto.board.request.delInquiryReq;
import com.plogging.domain.Board.service.inquiry.InquiryService;
import com.plogging.global.dto.ApplicationResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@Api(tags = "Inquiry API")
@RequestMapping("api/inquiry")
public class InquiryController {
    private final InquiryService inquiryService;

    /**
     * 게시글 조회 기록 삭제
     * @author 강신현
     */
    @ApiOperation(value = "게시글 조회 기록 삭제", notes = "")
    @DeleteMapping("/del")
    public ApplicationResponse<Void> delInquiry(@ModelAttribute delInquiryReq delInquiryReq){
        return inquiryService.delInquiry(delInquiryReq);
    }
}
