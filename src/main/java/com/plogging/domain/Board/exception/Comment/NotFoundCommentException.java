package com.plogging.domain.Board.exception.Comment;

public class NotFoundCommentException extends CommentException{
    public NotFoundCommentException(){
        super(CommentExceptionList.NOT_FOUND_ID.getCODE(),
                CommentExceptionList.NOT_FOUND_ID.getHTTPSTATUS(),
                CommentExceptionList.NOT_FOUND_ID.getMESSAGE());
    }
}
