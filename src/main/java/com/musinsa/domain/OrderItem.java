package com.musinsa.domain;

import java.math.BigDecimal;

public class OrderItem {

    private Long id;

    private String name;
    private BigDecimal price;
    private Integer quantity;

    public OrderItem(final Long id, final String name, final BigDecimal price, final Integer quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public static OrderItem of(final CartProduct cartProduct) {
        return new OrderItem(null, cartProduct.getName(), cartProduct.getPrice(), cartProduct.getQuantity());
    }

    public BigDecimal getAmount() {
        return price.multiply(BigDecimal.valueOf(quantity));
    }
}
