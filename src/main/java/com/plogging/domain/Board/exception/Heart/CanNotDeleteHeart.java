package com.plogging.domain.Board.exception.Heart;

public class CanNotDeleteHeart  extends HeartException {
    public CanNotDeleteHeart(){
        super(HeartExceptionList.CANT_CANCEL_HEART.getCODE(),
                HeartExceptionList.CANT_CANCEL_HEART.getHTTPSTATUS(),
                HeartExceptionList.CANT_CANCEL_HEART.getMESSAGE());
    }
}