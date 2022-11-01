package com.musinsa.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class OrderItemTest {

    @ParameterizedTest
    @ValueSource(ints = {-1, -100})
    void 주문수량이_음수이면_주문상품을_생성할_수_없다(int quantity) {
        assertThatThrownBy(() -> getOrderItem(1L, quantity, 1000))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 상품_id_없이_주문상품을_생성할_수_없다() {
        assertThatThrownBy(() -> getOrderItem(null, 10, 1000))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 주문상품의_금액을_계산한다() {
        final OrderItem orderItem = getOrderItem(1L, 10, 1000);
        assertThat(orderItem.getAmount()).isEqualTo(BigDecimal.valueOf(10000));
    }

    private OrderItem getOrderItem(final Long cartProductId,
                                   final Integer quantity,
                                   final int price) {
        return new OrderItem(cartProductId, quantity, BigDecimal.valueOf(price));
    }
}
