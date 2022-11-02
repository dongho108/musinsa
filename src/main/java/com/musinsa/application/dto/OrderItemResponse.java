package com.musinsa.application.dto;

import com.musinsa.domain.OrderItem;

public class OrderItemResponse {

    private final String name;
    private final String quantity;

    private OrderItemResponse(final String name, final String quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public static OrderItemResponse from(final OrderItem orderItem) {
        return new OrderItemResponse(orderItem.getName(), String.valueOf(orderItem.getQuantity()));
    }

    public String getName() {
        return name;
    }

    public String getQuantity() {
        return quantity;
    }
}
