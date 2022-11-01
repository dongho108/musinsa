package com.musinsa.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.Test;

class OrderItemsTest {

    @Test
    void 주문상품목록의_금액을_계산한다() {
        final CartProduct 무탠다드_반팔 = new CartProduct(1L, 10, 1L, "무탠다드 반팔", BigDecimal.valueOf(1000));
        final CartProduct 무탠다드_긴팔 = new CartProduct(1L, 20, 2L, "무탠다드 긴팔", BigDecimal.valueOf(2000));

        final OrderItems orderItems = OrderItems.from(List.of(무탠다드_반팔, 무탠다드_긴팔));
        assertThat(orderItems.calculateAmount()).isEqualTo(BigDecimal.valueOf(50000));
    }
}