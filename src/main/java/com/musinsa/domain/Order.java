package com.musinsa.domain;

import java.math.BigDecimal;

public class Order {

    private static final BigDecimal MINIMUM_FREE_DELIVERY_AMOUNT = BigDecimal.valueOf(50000);
    private static final BigDecimal DELIVERY_AMOUNT = BigDecimal.valueOf(2500);

    private Long id;
    private OrderItems orderItems;

    public Order(final OrderItems orderItems) {
        this.orderItems = orderItems;
    }

    public BigDecimal calculatePaymentAmount() {
        final BigDecimal amount = orderItems.calculateAmount();
        if (amount.compareTo(MINIMUM_FREE_DELIVERY_AMOUNT) < 0) {
            return amount.add(DELIVERY_AMOUNT);
        }
        return amount;
    }
}
