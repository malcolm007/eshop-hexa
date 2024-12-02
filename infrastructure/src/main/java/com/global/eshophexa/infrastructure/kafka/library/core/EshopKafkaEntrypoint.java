package com.global.eshophexa.infrastructure.kafka.library.core;

import com.global.eshophexa.infrastructure.kafka.library.jpa.EshopProducerJPA;
import com.global.eshophexa.infrastructure.kafka.library.models.KafkaEventDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class EshopKafkaEntrypoint {

    private final Map<String, KafkaPusher> producersMap;
    private final EshopProducerJPA eshopProducerJPA;

    public EshopKafkaEntrypoint(@Autowired(required = false) List<KafkaPusher> producers,
                                EshopProducerJPA eshopProducerJPA) {
        this.producersMap = producers.stream().collect(Collectors.toMap(KafkaPusher::getProducerCode,
                Function.identity()));
        this.eshopProducerJPA = eshopProducerJPA;
    }

    public void performPushToKafka() {
        producersMap.forEach((k, v) -> {
            List<KafkaEventDTO> events = eshopProducerJPA.findAllEventToSend(k);
             v.pushMessages(events);
            eshopProducerJPA.updateStatus(k, events);
        });
    }
}
