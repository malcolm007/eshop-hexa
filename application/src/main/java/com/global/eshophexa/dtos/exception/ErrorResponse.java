package com.global.eshophexa.dtos.exception;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ErrorResponse {

    private final int httpStatus;
    private final String message;
    private final String errorCode;
}
