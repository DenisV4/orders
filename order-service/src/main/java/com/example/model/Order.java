package com.example.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Order {

    private String product;

    private Integer quantity;
}
