package com.plogging.global.exception;


import com.plogging.global.dto.ApiErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> methodArgumentNotValidException(MethodArgumentNotValidException e){
        String errorCode = requireNonNull(e.getFieldError()).getDefaultMessage();

        log.warn(LOG_FORMAT, e.getClass().getSimpleName(), "V0001", errorCode);

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST.value())
                .body(new ApiErrorResponse("V0001", Arrays.asList(errorCode)));

    }

}
