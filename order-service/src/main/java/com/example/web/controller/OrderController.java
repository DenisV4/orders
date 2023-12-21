package com.example.web.controller;

import com.example.event.OrderEvent;
import com.example.model.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderController {
    private final KafkaTemplate<String, OrderEvent> kafkaTemplate;

    @Value("${app.kafka.topic}")
    private String topic;

    @PostMapping
    public String sendEvent(@RequestBody Order order) {
        var orderEvent = OrderEvent
                .builder()
                .product(order.getProduct())
                .quantity(order.getQuantity())
                .build();
        kafkaTemplate.send(topic, orderEvent);

        return "Order accepted";
    }
}
