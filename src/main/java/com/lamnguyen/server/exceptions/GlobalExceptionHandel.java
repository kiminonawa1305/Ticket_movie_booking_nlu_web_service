package com.lamnguyen.server.exceptions;

import com.lamnguyen.server.models.response.APIResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandel {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<APIResponse<String>> handleException(ApplicationException e) {
        APIResponse<String> body = APIResponse.<String>builder()
                .status(e.getErrorCode().code())
                .message(e.getErrorCode().message())
                .build();
        return ResponseEntity.badRequest().body(body);
    }
}
