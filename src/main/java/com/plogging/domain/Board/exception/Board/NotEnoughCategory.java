package com.plogging.domain.Board.exception.Board;

public class NotEnoughCategory extends BoardException{
    public NotEnoughCategory(){
        super(BoardExceptionList.MIN_CATEGORY.getCODE(),
                BoardExceptionList.MIN_CATEGORY.getHTTPSTATUS(),
                BoardExceptionList.MIN_CATEGORY.getMESSAGE());
    }
}
