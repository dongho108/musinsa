package com.musinsa.domain;

import static com.musinsa.fixture.Fixture.getCart;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.Test;

class CartTest {

    @Test
    void 장바구니_상품들이_null_이면_생성할_수_없다() {
        assertThatThrownBy(() -> getCart(1L, null))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 장바구니_상품들의_size_가_0이면_생성할_수_없다() {
        assertThatThrownBy(() -> getCart(1L, List.of()))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
