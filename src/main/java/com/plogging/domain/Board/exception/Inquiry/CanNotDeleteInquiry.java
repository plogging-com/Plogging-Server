package com.plogging.domain.Board.exception.Inquiry;

public class CanNotDeleteInquiry extends InquiryException {
    public CanNotDeleteInquiry(){
        super(InquiryExceptionList.CANT_DELETE_INQUIRY.getCODE(),
                InquiryExceptionList.CANT_DELETE_INQUIRY.getHTTPSTATUS(),
                InquiryExceptionList.CANT_DELETE_INQUIRY.getMESSAGE());
    }
}
