package com.global.eshophexa.infrastructure.kafka.library.core;

import com.global.eshophexa.infrastructure.kafka.library.models.KafkaEventDTO;

import java.util.List;

public interface KafkaPusher {

    void pushMessages(List<KafkaEventDTO> events);
    String getProducerCode();
}
