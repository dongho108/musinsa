package com.musinsa.domain;

import java.math.BigDecimal;

public class OrderItem {

    private Long id;
    private Long productId;
    private BigDecimal quantity;
    private BigDecimal price;

    public OrderItem(final Long productId, final BigDecimal quantity, final BigDecimal price) {
        validateExistProduct(productId);
        validateQuantity(quantity);
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
    }

    private void validateExistProduct(final Long productId) {
        if (productId == null) {
            throw new IllegalArgumentException("상품 id 없이 주문 상품을 생성할 수 없습니다.");
        }
    }

    private void validateQuantity(final BigDecimal quantity) {
        if (quantity.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("주문 수량이 음수이면 주문 상품을 생성할 수 없습니다.");
        }
    }
}
