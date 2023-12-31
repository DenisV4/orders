package com.example.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig {

    @Value("${app.kafka.topic}")
    private String topic;

    @Bean
    NewTopic newTopic() {
        return new NewTopic(topic, 1, (short) 1);
    }
}
