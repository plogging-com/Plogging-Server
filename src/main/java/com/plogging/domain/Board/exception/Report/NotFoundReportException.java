package com.plogging.domain.Board.exception.Report;

import com.plogging.domain.Board.exception.Heart.HeartExceptionList;

public class NotFoundReportException extends ReportException{
    public NotFoundReportException(){
        super(ReportExceptionList.NOT_FOUND_ID.getCODE(),
                ReportExceptionList.NOT_FOUND_ID.getHTTPSTATUS(),
                ReportExceptionList.NOT_FOUND_ID.getMESSAGE());
    }
}
