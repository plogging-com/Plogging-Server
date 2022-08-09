package com.plogging.domain.Quest.exception;

public class CanNotCompleteQuestException  extends QuestException {
    public CanNotCompleteQuestException() {
        super(QuestExceptionList.NOT_ENOUGH_GAGE.getCODE(),
                QuestExceptionList.NOT_ENOUGH_GAGE.getHTTPSTATUS(),
                QuestExceptionList.NOT_ENOUGH_GAGE.getMESSAGE());
    }
}
