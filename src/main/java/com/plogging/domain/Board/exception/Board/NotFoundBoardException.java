package com.plogging.domain.Board.exception.Board;

public class NotFoundBoardException extends BoardException{
    public NotFoundBoardException(){
        super(BoardExceptionList.NOT_FOUND_ID.getCODE(),
                BoardExceptionList.NOT_FOUND_ID.getHTTPSTATUS(),
                BoardExceptionList.NOT_FOUND_ID.getMESSAGE());
    }
}