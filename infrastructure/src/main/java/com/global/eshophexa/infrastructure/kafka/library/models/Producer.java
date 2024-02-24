package com.global.eshophexa.infrastructure.kafka.models;

import lombok.Data;

@Data
public class Producer {
    private boolean enabled;
    private String name;
    private String topic;
}
