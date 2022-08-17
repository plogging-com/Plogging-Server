package com.plogging.domain.Board.exception.Board;

public class OverMaxContentLength extends BoardException{
    public OverMaxContentLength(){
        super(BoardExceptionList.MAX_CONTENTSIZE.getCODE(),
                BoardExceptionList.MAX_CONTENTSIZE.getHTTPSTATUS(),
                BoardExceptionList.MAX_CONTENTSIZE.getMESSAGE());
    }
}
