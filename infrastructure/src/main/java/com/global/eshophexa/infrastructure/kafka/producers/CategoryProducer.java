package com.global.eshophexa.infrastructure.kafka.producers;

import com.global.eshophexa.infrastructure.kafka.library.annotations.EShopPush;
import com.global.eshophexa.infrastructure.kafka.library.core.EShopPushService;
import com.global.eshophexa.infrastructure.kafka.library.models.KafkaEventDTO;
import com.global.eshophexa.infrastructure.kafka.library.models.KafkaMessage;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@EShopPush(code = "category")
public class CategoryProducer implements EShopPushService {
    @Override
    public Map<Long, KafkaMessage> getDataToPush(List<KafkaEventDTO> events) {
        return events.stream().map(event -> {
            KafkaMessage kafkaMessage = new KafkaMessage();
            kafkaMessage.setKey(event.getId().toString());
            kafkaMessage.setPayload(event.toString());
            kafkaMessage.setId(event.getId());
            return kafkaMessage;
        }).collect(Collectors.toMap(KafkaMessage::getId, Function.identity()));
    }
}
