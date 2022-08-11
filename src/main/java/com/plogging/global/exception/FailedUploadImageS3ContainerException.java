package com.plogging.global.exception;

import org.springframework.http.HttpStatus;

public class FailedUploadImageS3ContainerException extends ApplicationException{

    public FailedUploadImageS3ContainerException() {
        super(
                GlobalExceptionList.FAIL_UPLOAD_IMAGE.getCODE(),
                GlobalExceptionList.FAIL_UPLOAD_IMAGE.getHTTPSTATUS(),
                GlobalExceptionList.FAIL_UPLOAD_IMAGE.getMESSAGE());
    }
}
