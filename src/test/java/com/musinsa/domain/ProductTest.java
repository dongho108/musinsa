package com.musinsa.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.math.BigDecimal;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ProductTest {

    @ParameterizedTest
    @ValueSource(ints = {-1, -100})
    void 상품의_판매가격이_음수이면_상품을_생성할_수_없다(int price) {
        assertThatThrownBy(() -> new Product("111", "29CM", BigDecimal.valueOf(price), 10))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, -100})
    void 상품의_재고가_음수이면_상품을_생성할_수_없다(int stock) {
        assertThatThrownBy(() -> new Product("111", "29CM", BigDecimal.valueOf(1000), stock))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
