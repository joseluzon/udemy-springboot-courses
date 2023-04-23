package com.github.joseluzon.udemy.springframework5.rest.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class TestKafkaListener {

    @KafkaListener(topics = "test-topic", groupId = "udemy-springframework5-rest")
    public void listenTextTopic(final String message) {
        log.info("listen test-topic : {}", message);
    }

}
