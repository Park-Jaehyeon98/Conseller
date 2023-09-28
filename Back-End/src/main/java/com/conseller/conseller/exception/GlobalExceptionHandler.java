package com.conseller.conseller.exception;

import com.conseller.conseller.utils.dto.CommonResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<CommonResponse> handleUserNameNotFoundException(CustomException exception) {
        CommonResponse commonResponse = CommonResponse.builder()
                .code(exception.getStatus().getCode())
                .message(exception.getStatus().getMessage())
                .build();

        return ResponseEntity.badRequest()
                .body(commonResponse);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleRuntimeException(RuntimeException exception) {
        CommonResponse commonResponse = CommonResponse.builder()
                .code(9999)
                .message(exception.getMessage())
                .build();

        return ResponseEntity.badRequest()
                .body(commonResponse);
    }
}
