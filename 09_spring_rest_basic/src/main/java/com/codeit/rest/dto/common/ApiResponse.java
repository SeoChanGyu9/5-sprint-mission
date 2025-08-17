package com.codeit.rest.dto.common;

// 응답 결과를 일정한 포멧으로서 처리하기 위한 응답 공통 설계


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {

    private boolean success; //응답 성공 여부
    private String message; // 응답 메세지
    private T data; //실제 body 영역
    private Instant timestamp; //REST 응답시간

    // 공통 API를 생성하기 위한 생성 메서드

    public static <T> ApiResponse<T> ok(T data){
        return ApiResponse.<T>builder()
                .success(true)
                .data(data)
                .timestamp(Instant.now())
                .build();
    }
    public static <T> ApiResponse<T> ok(T data, String message){
        return ApiResponse.<T>builder()
                .success(true)
                .data(data)
                .message(message)
                .timestamp(Instant.now())
                .build();
    }

    public static <T> ApiResponse<T> ok(String message){
        return ApiResponse.<T>builder()
                .success(true)
                .message(message)
                .timestamp(Instant.now())
                .build();
    }

}
