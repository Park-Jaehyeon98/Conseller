package com.conseller.conseller.exception;

import com.conseller.conseller.utils.dto.CommonResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final String COMMON_CODE = "0001";
    private static final String IllEGAL_CODE = "0002";
    private static final String USER_NOT_FOUND_CODE = "0003";

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<CommonResponse> handleRuntimeException(RuntimeException exception) {
        CommonResponse commonResponse = CommonResponse.builder()
                .code(COMMON_CODE)
                .message(exception.getMessage())
                .build();

        return ResponseEntity.badRequest()
                .body(commonResponse);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<CommonResponse> handleIlligalException(IllegalArgumentException exception) {
        CommonResponse commonResponse = CommonResponse.builder()
                .code(IllEGAL_CODE)
                .message(exception.getMessage())
                .build();

        return ResponseEntity.badRequest()
                .body(commonResponse);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<CommonResponse> handleUserNameNotFoundException(RuntimeException exception) {
        CommonResponse commonResponse = CommonResponse.builder()
                .code(USER_NOT_FOUND_CODE)
                .message(exception.getMessage())
                .build();

        return ResponseEntity.badRequest()
                .body(commonResponse);
    }
}
