package com.prudhviraj.ecommerce.kafka.notification_svc.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

/**
 * Kafka consumer service for handling messages from the user topic.
 * This service contains multiple methods for processing messages received from the Kafka topic.
 */
@Service
@Slf4j
public class UserKafkaConsumer {

    /**
     * Handles messages from the user-topic using the first listener method.
     *
     * @param message the message received from the Kafka topic.
     */
    @KafkaListener(topics = {"user-topic"})
    public void handleUserTopic(String message) {
        log.info("Message received in method 0: {}", message);
    }

    @KafkaListener(topics = {"user-topic"})
    public void handleUserTopic1(String message) {
        log.info("Message received in method 1: {}", message);
    }

    @KafkaListener(topics = {"user-topic"})
    public void handleUserTopic2(String message) {
        log.info("Message received in method 2: {}", message);
    }
}


