package com.global.eshophexa.exceptions.handler;

import com.global.eshophexa.dtos.exception.ErrorResponse;
import com.global.eshophexa.infrastructure.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Locale;

@ControllerAdvice
@RequiredArgsConstructor
public class EshopExceptionHandler {

    private final MessageSource messageSource;


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleException(ResourceNotFoundException e, Locale locale) {
        return ResponseEntity.status(e.getHttpStatus())
                .body(ErrorResponse.builder()
                        .errorCode(e.getErrorCode())
                        .httpStatus(e.getHttpStatus())
                        .message(messageSource.getMessage(e.getMessage(), e.getParams(), locale))
                        .build());
    }
}
