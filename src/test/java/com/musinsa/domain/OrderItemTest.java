package com.musinsa.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class OrderItemTest {

    @ParameterizedTest
    @ValueSource(ints = {-1, -100})
    void 주문수량이_음수이면_주문상품을_생성할_수_없다(int quantity) {
        assertThatThrownBy(() -> new OrderItem(1L, BigDecimal.valueOf(quantity), BigDecimal.valueOf(1000)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 상품_id_없이_주문상품을_생성할_수_없다() {
        assertThatThrownBy(() -> new OrderItem(null, BigDecimal.valueOf(1000), BigDecimal.valueOf(1000)))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
