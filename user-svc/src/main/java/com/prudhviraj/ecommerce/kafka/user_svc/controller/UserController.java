package com.prudhviraj.ecommerce.kafka.user_svc.controller;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * REST controller for managing user-related operations.
 * This controller handles requests to send messages to a Kafka topic.
 */
@RestController
@RequestMapping("/users")
@Slf4j
@RequiredArgsConstructor
public class UserController {
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Value("${kafka.topic.user-topic}")
    private String KAFKA_USER_TOPIC;

    /**
     * Endpoint to send a message to the Kafka topic.
     *
     * @param message the message to be sent.
     * @return a response entity indicating the result of the operation.
     */
    @PostMapping("/getMessage/{message}")
    public ResponseEntity<String> getMessage(@PathVariable String message) {
        log.info("Received message to send: {}", message);

        // Send multiple messages to the Kafka topic
        for (int i = 0; i < 100; i++) {
            String key = String.valueOf(i % 2); // Key alternates between 0 and 1
            String value = message + " count:" + i; // Message value includes the count
            kafkaTemplate.send(KAFKA_USER_TOPIC, key, value);
        }
        return ResponseEntity.ok("Messages queued");
    }
}


