package com.musinsa.domain;

import static com.musinsa.fixture.Fixture.getCartProductRequest;
import static com.musinsa.fixture.Fixture.getOrderItemRequest;
import static com.musinsa.fixture.Fixture.getProduct;
import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

class OrderItemTest {

    @Test
    void 주문상품의_금액을_계산한다() {
        final OrderItem orderItem = getOrderItemRequest(getCartProductRequest(getProduct(1L, 1000), 10));
        assertThat(orderItem.getAmount()).isEqualTo(BigDecimal.valueOf(10000));
    }
}
