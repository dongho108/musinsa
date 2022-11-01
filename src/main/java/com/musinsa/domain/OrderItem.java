package com.musinsa.domain;

import java.math.BigDecimal;

public class OrderItem {

    private Long id;
    private Long cartProductId;
    private Integer quantity;
    private BigDecimal price;

    public OrderItem(final Long cartProductId, final Integer quantity, final BigDecimal price) {
        validateExistProduct(cartProductId);
        validateQuantity(quantity);
        this.cartProductId = cartProductId;
        this.quantity = quantity;
        this.price = price;
    }

    private void validateExistProduct(final Long productId) {
        if (productId == null) {
            throw new IllegalArgumentException("상품 id 없이 주문 상품을 생성할 수 없습니다.");
        }
    }

    private void validateQuantity(final Integer quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("주문 수량이 음수이면 주문 상품을 생성할 수 없습니다.");
        }
    }

    public BigDecimal getAmount() {
        return price.multiply(BigDecimal.valueOf(quantity));
    }
}
