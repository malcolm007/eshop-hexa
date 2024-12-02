package com.global.eshophexa.infrastructure.kafka.library.models;

import lombok.Data;

@Data
public class Producer {
    private boolean enabled;
    private String name;
    private String topic;
    private Class<?> classe;
}
