package com.example.event;

import lombok.*;

import java.time.Instant;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderStatusEvent {

    private String status;

    private Instant date;
}
