package com.example.listener;

import com.example.event.OrderStatusEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
public class OrderStatusEventListener {

    @KafkaListener(topics = "order-status-topic")
    public void onOrderStatusEvent(@Payload OrderStatusEvent event,
                                   @Header(value = KafkaHeaders.RECEIVED_KEY, required = false) UUID key,
                                   @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                                   @Header(KafkaHeaders.RECEIVED_PARTITION) Integer partition,
                                   @Header(KafkaHeaders.RECEIVED_TIMESTAMP) Long timestamp) {

        log.info("Received order status event: {}", event);
        log.info("Key: {}; Topic {}; Partition: {}; Timestamp: {}", key, topic, partition, timestamp);
    }
}
