package com.plogging.domain.Quest.exception;

public class QuestNameNotFoundException extends QuestException {
    public QuestNameNotFoundException(String name) {
        super(QuestExceptionList.NOT_FOUND_ID.getCODE(),
                QuestExceptionList.NOT_FOUND_ID.getHTTPSTATUS(),
                QuestExceptionList.NOT_FOUND_ID.getMESSAGE());
    }
}
