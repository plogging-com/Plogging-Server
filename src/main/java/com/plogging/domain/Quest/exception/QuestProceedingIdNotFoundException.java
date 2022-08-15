package com.plogging.domain.Quest.exception;

public class QuestProceedingIdNotFoundException extends QuestException {
    public QuestProceedingIdNotFoundException(Long id) {
        super(QuestExceptionList.NOT_FOUND_PROCEEDING.getCODE(),
                QuestExceptionList.NOT_FOUND_PROCEEDING.getHTTPSTATUS(),
                QuestExceptionList.NOT_FOUND_PROCEEDING.getMESSAGE());
    }
}
