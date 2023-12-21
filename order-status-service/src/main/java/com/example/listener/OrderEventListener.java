package com.example.listener;

import com.example.event.OrderEvent;
import com.example.event.OrderStatusEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;
import java.util.Random;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderEventListener {
    private final KafkaTemplate<String, OrderStatusEvent> kafkaTemplate;

    private final List<String> statuses = List.of("PROCESS", "COMPLETED", "FAILED", "CREATED");
    private final Random random = new Random();

    @Value("${app.kafka.topic}")
    private String topic;

    @KafkaListener(topics = "order-topic")
    public void onOrderEvent(OrderEvent event) {
        log.info("Received order event: {}", event);

        var orderStatusEvent = OrderStatusEvent
                .builder()
                .status(statuses.get(random.nextInt(3)))
                .date(Instant.now())
                .build();

        kafkaTemplate.send(topic, orderStatusEvent);
    }
}
