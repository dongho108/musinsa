package com.musinsa.domain;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class OrderItems {

    private List<OrderItem> orderItems;

    private OrderItems(final List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public static OrderItems from(final List<CartProduct> cartProducts) {
        return new OrderItems(mapToOrderItems(cartProducts));
    }

    private static List<OrderItem> mapToOrderItems(final List<CartProduct> cartProducts) {
        return cartProducts.stream()
                .map(it -> new OrderItem(it.getId(), it.getQuantity(), it.getPrice()))
                .collect(Collectors.toList());
    }

    public BigDecimal calculateAmount() {
        BigDecimal sum = BigDecimal.ZERO;
        for (final OrderItem orderItem : orderItems) {
            sum = sum.add(orderItem.getAmount());
        }
        return sum;
    }
}
