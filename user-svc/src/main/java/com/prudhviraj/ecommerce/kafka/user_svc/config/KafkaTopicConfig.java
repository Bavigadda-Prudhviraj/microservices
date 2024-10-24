package com.prudhviraj.ecommerce.kafka.user_svc.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import lombok.extern.slf4j.Slf4j;

/**
 * Configuration class for Kafka topics.
 * This class creates and configures the necessary Kafka topics for the application.
 */
@Configuration
@Slf4j
public class KafkaTopicConfig {

    @Value("${kafka.topic.user-topic}")
    private String KAFKA_USER_TOPIC;

    /**
     * Creates a new Kafka topic for user messages.
     *
     * @return a NewTopic object representing the user topic with 3 partitions and a replication factor of 1.
     */
    @Bean
    public NewTopic userRandomTopic() {
        log.info("Creating Kafka topic: {}", KAFKA_USER_TOPIC);
        NewTopic newTopic = new NewTopic(KAFKA_USER_TOPIC, 3, (short) 1);
        log.info("Kafka topic {} created with {} partitions and replication factor {}", KAFKA_USER_TOPIC, 3, 1);
        return newTopic;
    }
}


