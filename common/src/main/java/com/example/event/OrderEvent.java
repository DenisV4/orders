package com.example.event;

import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderEvent {

    private String product;

    private Integer quantity;
}
