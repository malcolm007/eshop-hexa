package com.global.eshophexa.infrastructure.kafka.library.core;

import com.global.eshophexa.infrastructure.kafka.library.exception.KafkaCOnfigurationException;
import com.global.eshophexa.infrastructure.kafka.library.models.KafkaEventDTO;
import com.global.eshophexa.infrastructure.kafka.library.models.KafkaMessage;
import com.global.eshophexa.infrastructure.kafka.library.models.Producer;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.List;
import java.util.Map;

public class KafkaPusherImpl implements KafkaPusher {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final Producer producer;

    private final EShopPushService eShopPushService;

    public KafkaPusherImpl(KafkaTemplate<String, String> kafkaTemplate, Producer producer, EShopPushService eShopPushService) {
        this.kafkaTemplate = kafkaTemplate;
        this.producer = producer;
        this.eShopPushService = eShopPushService;
    }

    @Override
    public void pushMessages(List<KafkaEventDTO> events) {
        Map<Long, KafkaMessage> kafkaMessages = eShopPushService.getDataToPush(events);
        events.forEach(event -> {
            try {
                if (kafkaMessages.containsKey(event.getId())) {
                    KafkaMessage kafkaMessage = kafkaMessages.get(event.getId());
                    var completableFuture = this.kafkaTemplate.send(producer.getTopic(), kafkaMessage.getKey(), kafkaMessage.getPayload());
                    completableFuture.get();
                    event.setStatus("OK");
                } else {
                    throw new KafkaCOnfigurationException("Kafka message id not returned by client !");
                }
            } catch (Exception e) {
                event.setStatus("KO");
            }
        });
    }

    @Override
    public String getProducerCode() {
        return this.producer.getName();
    }
}