package com.plogging.domain.Quest.exception;

public class QuestIdEmptyException extends QuestException {
    public QuestIdEmptyException() {
        super(QuestExceptionList.ALL_EMPTY.getCODE(),
                QuestExceptionList.ALL_EMPTY.getHTTPSTATUS(),
                QuestExceptionList.ALL_EMPTY.getMESSAGE());
    }
}
