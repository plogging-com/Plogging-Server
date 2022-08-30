package com.plogging.domain.Board.exception.Comment;


public class EmptyContentException extends CommentException{
    public EmptyContentException(){
        super(CommentExceptionList.EMPTY_CONTENT.getCODE(),
                CommentExceptionList.EMPTY_CONTENT.getHTTPSTATUS(),
                CommentExceptionList.EMPTY_CONTENT.getMESSAGE());
    }
}
