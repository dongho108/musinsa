package com.musinsa.domain;

import static com.musinsa.fixture.Fixture.getCart;
import static com.musinsa.fixture.Fixture.getCartProductRequest;
import static com.musinsa.fixture.Fixture.getProduct;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.Test;

class CartTest {

    @Test
    void 장바구니_상품들이_null_이면_생성할_수_없다() {
        assertThatThrownBy(() -> Cart.of(1L, null))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 장바구니_상품들의_size_가_0이면_생성할_수_없다() {
        assertThatThrownBy(() -> Cart.of(1L, List.of()))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 장바구니에_상품을_추가한다() {
        final Cart cart = getCart(1L, List.of(getCartProductRequest(getProduct(), 1)));
        cart.add(getCartProductRequest(getProduct(), 1));
        assertThat(cart.getCartProducts()).hasSize(2);
    }

    @Test
    void 장바구니를_비운다() {
        final List<CartProduct> cartProducts = List.of(getCartProductRequest(getProduct(), 1), getCartProductRequest(getProduct(), 1));
        final Cart cart = getCart(1L, cartProducts);
        cart.clear();
        assertThat(cart.getCartProducts()).hasSize(0);
    }
}
