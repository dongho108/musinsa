package com.musinsa.domain;

import java.math.BigDecimal;

public class CartProduct {

    private Long id;
    private Integer quantity;

    private Long productId;
    private String name;
    private BigDecimal price;

    public CartProduct(final Long id,
                       final Integer quantity,
                       final Long productId,
                       final String name,
                       final BigDecimal price) {
        validateQuantity(quantity);
        validatePrice(price);
        this.id = id;
        this.quantity = quantity;
        this.productId = productId;
        this.name = name;
        this.price = price;
    }

    private void validatePrice(final BigDecimal price) {
        if (price.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("가격이 음수이면 장바구니 상품을 생성할 수 없습니다.");
        }
    }

    private void validateQuantity(final Integer quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("수량이 음수이면 장바구니 상품을 생성할 수 없습니다.");
        }
    }

    public Long getId() {
        return id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Long getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
