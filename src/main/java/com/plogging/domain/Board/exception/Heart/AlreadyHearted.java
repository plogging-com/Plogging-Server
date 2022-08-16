package com.plogging.domain.Board.exception.Heart;

public class AlreadyHearted extends HeartException {
    public AlreadyHearted(){
        super(HeartExceptionList.ALREADY_HERATED.getCODE(),
                HeartExceptionList.ALREADY_HERATED.getHTTPSTATUS(),
                HeartExceptionList.ALREADY_HERATED.getMESSAGE());
    }
}