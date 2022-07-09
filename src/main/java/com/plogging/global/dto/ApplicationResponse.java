package com.plogging.global.dto;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApplicationResponse<T> {

    private boolean success;
    private int httpCode;
    private LocalDateTime localDateTime;
    private HttpStatus httpStatus;
    private String message;
    private T data;

    public static <T> ApplicationResponse<T> create(String message, T data){
        return (ApplicationResponse<T>) ApplicationResponse.builder()
                .success(true)
                .httpCode(HttpStatus.CREATED.value())
                .localDateTime(LocalDateTime.now())
                .message(message)
                .httpStatus(HttpStatus.CREATED)
                .data(data)
                .build();
    }

    public static <T> ApplicationResponse<T> ok(){
        return (ApplicationResponse<T>) ApplicationResponse.builder()
                .data(null)
                .localDateTime(LocalDateTime.now())
                .message("성공")
                .httpStatus(HttpStatus.OK)
                .build();
    }


}
