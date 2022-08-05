package com.plogging.global.dto;

import com.plogging.global.exception.ApplicationException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApplicationErrorResponse<T> {

    private boolean success;
    private int httpCode;
    private LocalDateTime localDateTime;
    private HttpStatus httpStatus;
    private String message;
//    private T data;


    public static <T> ApplicationErrorResponse<T> error(ApplicationException e){
        return (ApplicationErrorResponse<T>) ApplicationErrorResponse.builder()
                .success(false)
                .httpCode(e.getHttpStatus().value())
                .localDateTime(LocalDateTime.now())
                .httpStatus(e.getHttpStatus())
                .message(e.getMessage())
                .build();
    }
}