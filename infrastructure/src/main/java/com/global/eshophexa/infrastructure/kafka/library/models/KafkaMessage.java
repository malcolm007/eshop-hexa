package com.global.eshophexa.infrastructure.kafka.library.models;

import lombok.Data;

@Data
public class KafkaMessage {

    private Long id;
    private String key;
    private String payload;
}
