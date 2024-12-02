package com.global.eshophexa.infrastructure.kafka.library.models;

import lombok.Data;

@Data
public class KafkaEventDTO {

    private Long id;
    private Long entityId;
    private String status;
}
