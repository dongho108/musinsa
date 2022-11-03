package kr.co._29cm.homework.domain;

import static kr.co._29cm.homework.fixture.Fixture.getCart;
import static kr.co._29cm.homework.fixture.Fixture.getCartProductRequest;
import static kr.co._29cm.homework.fixture.Fixture.getProduct;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

class CartTest {

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
