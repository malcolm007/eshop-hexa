package com.global.eshophexa.infrastructure.exceptions;

public class ResourceNotFoundException extends EshopGenericException {

    public ResourceNotFoundException(int httpStatus, String message, String errorCode, Object[] params) {
        super(httpStatus, message, errorCode, params);
    }
}
