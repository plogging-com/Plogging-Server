package com.plogging.domain.Board.exception.Report;

public class AlreadyStatusException extends ReportException{
    public AlreadyStatusException(){
        super(ReportExceptionList.ALREAD_STATUS.getCODE(),
                ReportExceptionList.ALREAD_STATUS.getHTTPSTATUS(),
                ReportExceptionList.ALREAD_STATUS.getMESSAGE());
    }
}
