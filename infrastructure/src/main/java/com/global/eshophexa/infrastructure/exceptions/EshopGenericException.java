package com.global.eshophexa.infrastructure.exceptions;

import lombok.Getter;

@Getter
public class EshopGenericException extends RuntimeException{

    private final int httpStatus;
    private final String message;
    private final String errorCode;

    public EshopGenericException(int httpStatus, String message, String errorCode) {
        this.httpStatus = httpStatus;
        this.message = message;
        this.errorCode = errorCode;
    }

    public EshopGenericException(String message, Throwable cause, int httpStatus, String errorCode) {
        super(message, cause);
        this.httpStatus = httpStatus;
        this.message = message;
        this.errorCode = errorCode;
    }
}
