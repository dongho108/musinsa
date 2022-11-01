package com.musinsa.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

class CartProductTest {

    @Test
    void 수량이_음수이면_생성할_수_없다() {
        assertThatThrownBy(() -> new CartProduct(1L, -1, 1L, "무탠다드", BigDecimal.valueOf(1000)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 가격이_음수이면_생성할_수_없다() {
        assertThatThrownBy(() -> new CartProduct(1L, 10, 1L, "무탠다드", BigDecimal.valueOf(-1000)))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
