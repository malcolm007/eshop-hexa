package com.global.eshophexa.infrastructure.kafka.library.models;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Optional;

@ConfigurationProperties(value="eshop.kafka")
@Data
public class KafkaProperties {

    private List<Producer> producers;

    public Optional<Producer> getProducerByName(String name) {
        return producers.stream().filter(producer -> name.equals(producer.getName())).findFirst();
    }

}