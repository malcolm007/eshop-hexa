package com.global.eshophexa.exceptions.handler;

import com.global.eshophexa.dtos.exception.ErrorResponse;
import com.global.eshophexa.infrastructure.exceptions.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class EshopExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleException(ResourceNotFoundException e) {
        return ResponseEntity.status(e.getHttpStatus())
                .body(ErrorResponse.builder()
                        .errorCode(e.getErrorCode())
                        .httpStatus(e.getHttpStatus())
                        .message(e.getMessage())
                        .build());
    }
}
