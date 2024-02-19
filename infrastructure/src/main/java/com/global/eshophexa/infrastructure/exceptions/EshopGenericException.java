package com.global.eshophexa.infrastructure.exceptions;

import lombok.Getter;

@Getter
public class EshopGenericException extends RuntimeException{

    private final int httpStatus;
    private final String message;
    private final String errorCode;
    private final transient Object[] params;

    public EshopGenericException(int httpStatus, String message, String errorCode, Object[] params) {
        this.httpStatus = httpStatus;
        this.message = message;
        this.errorCode = errorCode;
        this.params = params;
    }
}
