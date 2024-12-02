package com.global.eshophexa.infrastructure.kafka.library.core;

import com.global.eshophexa.infrastructure.kafka.library.models.KafkaEventDTO;
import com.global.eshophexa.infrastructure.kafka.library.models.KafkaMessage;

import java.util.List;
import java.util.Map;

public interface EShopPushService {
    Map<Long, KafkaMessage> getDataToPush(List<KafkaEventDTO> eventDTOS);
}
