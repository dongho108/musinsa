package com.musinsa.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.Test;

class OrderItemsTest {

    @Test
    void 주문상품목록의_금액을_계산한다() {
        final CartProduct 반팔 = new CartProduct(1L, 10, 1L, "29CM 반팔", BigDecimal.valueOf(1000));
        final CartProduct 긴팔 = new CartProduct(1L, 20, 2L, "29CM 긴팔", BigDecimal.valueOf(2000));

        final OrderItems orderItems = OrderItems.from(List.of(반팔, 긴팔));
        assertThat(orderItems.calculateAmount()).isEqualTo(BigDecimal.valueOf(50000));
    }

    @Test
    void 장바구니상품들이_null_이면_생성할_수_없다() {
        assertThatThrownBy(() -> OrderItems.from(null))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 장바구니상품들의_크기가_0이면_생성할_수_없다() {
        assertThatThrownBy(() -> OrderItems.from(List.of()))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
