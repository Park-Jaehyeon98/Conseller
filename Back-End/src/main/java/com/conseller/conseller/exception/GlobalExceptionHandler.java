package com.conseller.conseller.exception;

import com.conseller.conseller.utils.dto.CommonResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleRuntimeException(RuntimeException exception) {
        CommonResponse commonResponse = CommonResponse.builder()
                .code(9999)
                .message(exception.getMessage())
                .build();

        return ResponseEntity.badRequest()
                .body(exception.getMessage());
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<CommonResponse> handleUserNameNotFoundException(CustomExceptionStatus exception) {
        CommonResponse commonResponse = CommonResponse.builder()
                .code(exception.getCode())
                .message(exception.getMessage())
                .build();

        return ResponseEntity.badRequest()
                .body(commonResponse);
    }
}
