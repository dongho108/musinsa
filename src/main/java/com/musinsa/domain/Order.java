package com.musinsa.domain;

import java.math.BigDecimal;

public class Order {

    private static final BigDecimal MINIMUM_FREE_DELIVERY_AMOUNT = BigDecimal.valueOf(50000);
    private static final BigDecimal DELIVERY_AMOUNT = BigDecimal.valueOf(2500);

    private Long id;
    private Long cartId;
    private OrderItems orderItems;

    public Order(final Long cartId, final OrderItems orderItems) {
        if (cartId == null) {
            throw new IllegalArgumentException("cartId가 없으면 주문을 생성할 수 없습니다.");
        }
        this.cartId = cartId;
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
