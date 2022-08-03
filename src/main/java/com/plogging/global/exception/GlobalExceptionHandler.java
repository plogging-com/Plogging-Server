package com.plogging.global.exception;


import com.plogging.global.dto.ApiErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final String LOG_FORMAT ="Class : {}, CODE : {}, MESSAGE : {}";
    private static final String INTERNAL_SERVER_ERROR_CODE = "S0001";

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ApiErrorResponse> applicationException(ApplicationException e){
        String errorCode = e.getErrorCode();

        log.warn(
                LOG_FORMAT,
                e.getClass().getSimpleName(),
                errorCode,
                e.getMessage()
        );
        return ResponseEntity
                .status(e.getHttpStatus())
                .body(new ApiErrorResponse(errorCode, Arrays.asList(e.getMessage())));
    }

}
