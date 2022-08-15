package com.plogging.global.exception;


import com.plogging.global.dto.ApiErrorResponse;

import com.plogging.global.dto.ApplicationErrorResponse;

import com.plogging.global.dto.ApplicationResponse;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;

import static java.util.Objects.requireNonNull;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final String LOG_FORMAT ="Class : {}, CODE : {}, MESSAGE : {}";
    private static final String INTERNAL_SERVER_ERROR_CODE = "S0001";

    @ExceptionHandler(ApplicationException.class)
    public ApplicationErrorResponse<Void> applicationException(ApplicationException e){
        String errorCode = e.getErrorCode();

        log.warn(
                LOG_FORMAT,
                e.getClass().getSimpleName(),
                errorCode,
                e.getMessage()
        );
        return ApplicationErrorResponse.error(e);
    }

    @ExceptionHandler(FailedUploadImageS3ContainerException.class)
    public ApplicationErrorResponse<Void> failedUploadImageS3ContainerException(FailedUploadImageS3ContainerException e){
        log.warn(LOG_FORMAT, e.getClass().getSimpleName(), "V0001",  e.getErrorCode());
        return ApplicationErrorResponse.error(e);
    }


}
