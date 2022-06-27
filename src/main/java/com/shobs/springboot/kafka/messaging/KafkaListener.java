package com.shobs.springboot.kafka.messaging;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.core.task.TaskExecutor;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.concurrent.CompletableFuture;

@Component
@Slf4j
public class KafkaListener {

    @Autowired
    TaskExecutor executor;

    @StreamListener(TransactionProcessorChannels.CTNS_POSTED_TRANSACTION_UNENCRYPTED_CONSUME_CHANNEL)
    public void tnsListenFromKafka(Message<String> message) {
        log.info("timestamp start {}", Timestamp.from(Instant.now()));
        CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            log.info("Timestamp {}, Transaction payload listened from unencrypted kafka : {}", Timestamp.from(Instant.now()), message.getPayload());
        });
        log.info("timestamp end {}", Timestamp.from(Instant.now()));
        Acknowledgment acknowledgment = message.getHeaders().get(KafkaHeaders.ACKNOWLEDGMENT, Acknowledgment.class);
        if (acknowledgment != null) {
            acknowledgment.acknowledge();
        }
    }
}
