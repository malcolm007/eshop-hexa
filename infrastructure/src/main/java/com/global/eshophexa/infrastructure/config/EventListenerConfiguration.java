package com.global.eshophexa.infrastructure.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.task.ThreadPoolTaskExecutorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

@Configuration
@EnableIntegration
public class EventListenerConfiguration {

    private static final Logger log = LoggerFactory.getLogger(EventListenerConfiguration.class);

    @Bean
    TaskExecutor eventTaskExecutor() {
        return new ThreadPoolTaskExecutorBuilder()
                .corePoolSize(30)
                .threadNamePrefix("event_handler")
                .build();
    }

    @Bean
    @ServiceActivator(inputChannel = "eventQueueChannel", async = "true", poller =
            @Poller(fixedDelay = "10", taskExecutor = "eventTaskExecutor"))
    public MessageHandler handleMessage() {
        return message -> {
            log.info((String) message.getPayload());
        };
    }

    @Bean
    public MessageChannel eventQueueChannel() {
        return new QueueChannel(3000);
    }
}
