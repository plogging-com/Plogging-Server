package com.plogging.domain.Quest.exception;

import com.plogging.domain.User.exception.UserException;
import com.plogging.domain.User.exception.UserExceptionList;

public class QuestIdNotFoundException extends QuestException {
    public QuestIdNotFoundException(Long id) {
        super(QuestExceptionList.NOT_FOUND_ID.getCODE(),
                QuestExceptionList.NOT_FOUND_ID.getHTTPSTATUS(),
                QuestExceptionList.NOT_FOUND_ID.getMESSAGE());
    }
}
