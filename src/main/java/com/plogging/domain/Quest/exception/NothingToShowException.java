package com.plogging.domain.Quest.exception;

public class NothingToShowException extends QuestException {
    public NothingToShowException() {
        super(QuestExceptionList.NOTHING_TO_SHOW.getCODE(),
                QuestExceptionList.NOTHING_TO_SHOW.getHTTPSTATUS(),
                QuestExceptionList.NOTHING_TO_SHOW.getMESSAGE());
    }
}
