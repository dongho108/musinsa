package com.musinsa.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.Test;

class OrderItemsTest {

    @Test
    void 주문상품목록의_금액을_계산한다() {
        final OrderItem orderItem1 = new OrderItem(1L, BigDecimal.valueOf(10), BigDecimal.valueOf(1000));
        final OrderItem orderItem2 = new OrderItem(2L, BigDecimal.valueOf(20), BigDecimal.valueOf(2000));

        final OrderItems orderItems = new OrderItems(List.of(orderItem1, orderItem2));
        assertThat(orderItems.calculateAmount()).isEqualTo(BigDecimal.valueOf(50000));
    }
}