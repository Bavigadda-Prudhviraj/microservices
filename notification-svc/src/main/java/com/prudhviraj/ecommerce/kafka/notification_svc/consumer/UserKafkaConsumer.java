package com.prudhviraj.ecommerce.kafka.notification_svc.consumer;

import com.prudhviraj.ecommerce.kafka.user_svc.event.UserCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * Kafka consumer service for handling messages from the user topic.
 * This service contains multiple methods for processing messages received from the Kafka topic.
 */
@Service

public class UserKafkaConsumer {
    private static final Logger log = LoggerFactory.getLogger(UserKafkaConsumer.class);

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



    /**
     * Handles incoming UserCreatedEvent messages from the 'user-created-topic'.
     *
     * This method logs the received event and attempts to process it.
     * If an error occurs during processing, it logs the error details.
     *
     * @param userCreatedEvent the event containing details about the newly created user
     */
    @KafkaListener(topics = {"user-created-topic"})
    public void handleUserCreatedTopic(UserCreatedEvent userCreatedEvent) {
        log.info("Received User from user-created-topic: {}", userCreatedEvent);

        try {
            /* TODO: Add processing logic here (e.g., save to database, send notification, etc) */
            log.info("Processing UserCreatedEvent for User ID: {}", userCreatedEvent.getId());
        } catch (Exception e) {
            log.error("Error processing UserCreatedEvent for User ID {}: {}",
                    userCreatedEvent.getId(), e.getMessage(), e);
        }
    }
}


