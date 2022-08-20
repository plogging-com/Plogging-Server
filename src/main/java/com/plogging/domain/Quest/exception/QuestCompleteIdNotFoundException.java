package com.plogging.domain.Quest.exception;

public class QuestCompleteIdNotFoundException extends QuestException {
    public QuestCompleteIdNotFoundException(Long id) {
        super(QuestExceptionList.NOT_FOUND_COMPLTE_ID.getCODE(),
                QuestExceptionList.NOT_FOUND_COMPLTE_ID.getHTTPSTATUS(),
                QuestExceptionList.NOT_FOUND_COMPLTE_ID.getMESSAGE());
    }
}
