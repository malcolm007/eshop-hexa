package com.global.eshophexa.infrastructure.scheduler;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

@Component
@RequiredArgsConstructor
public class Scheduler {

    private static final Logger log = LoggerFactory.getLogger(Scheduler.class);

    private final MessageChannel eventQueueChannel;

    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        String date = dateFormat.format(new Date());
        boolean addedToQueue = eventQueueChannel.send(MessageBuilder.createMessage(date, new MessageHeaders(new HashMap<>())), 5000);
        log.info("message added to Queue : {}", addedToQueue);
    }
}
