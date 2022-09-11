package com.plogging.domain.User.exception;

import static com.plogging.domain.User.exception.UserExceptionList.*;

public class ValidUserFindPagingException extends UserException{

    public ValidUserFindPagingException() {
            super(VAILD_USER_FINDPAGING.getCODE(),
                VAILD_USER_FINDPAGING.getHTTPSTATUS(),
                VALID_FORM_NICKNAME.getMESSAGE());
    }

}
