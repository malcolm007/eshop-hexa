package com.global.eshophexa.infrastructure.kafka.exception;

public class KafkaCOnfigurationException extends RuntimeException{

    public KafkaCOnfigurationException() {
    }

    public KafkaCOnfigurationException(String message) {
        super(message);
    }

    public KafkaCOnfigurationException(String message, Throwable cause) {
        super(message, cause);
    }
}
