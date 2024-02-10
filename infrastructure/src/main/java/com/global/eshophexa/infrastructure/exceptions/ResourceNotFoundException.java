package com.global.eshophexa.infrastructure.exceptions;

public class ResourceNotFoundException extends EshopGenericException {

    public ResourceNotFoundException(int httpStatus, String message, String errorCode) {
        super(httpStatus, message, errorCode);
    }

    public ResourceNotFoundException(String message, Throwable cause, int httpStatus, String errorCode) {
        super(message, cause, httpStatus, errorCode);
    }
}
