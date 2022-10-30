package com.musinsa.domain;

import java.math.BigDecimal;
import java.util.List;

public class OrderItems {

    private List<OrderItem> orderItems;

    public OrderItems(final List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public BigDecimal calculateAmount() {
        BigDecimal sum = BigDecimal.ZERO;
        for (final OrderItem orderItem : orderItems) {
            sum = sum.add(orderItem.getAmount());
        }
        return sum;
    }
}
