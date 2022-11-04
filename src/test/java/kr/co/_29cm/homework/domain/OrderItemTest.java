package kr.co._29cm.homework.domain;

import static kr.co._29cm.homework.fixture.Fixture.getCartProductRequest;
import static kr.co._29cm.homework.fixture.Fixture.getOrderItemRequest;
import static kr.co._29cm.homework.fixture.Fixture.getProduct;
import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

class OrderItemTest {

    @Test
    void 주문상품의_금액을_계산한다() {
        final OrderItem orderItem = getOrderItemRequest(getCartProductRequest(getProduct(1L, 1000), 10));
        assertThat(orderItem.getAmount()).isEqualTo(BigDecimal.valueOf(10000));
    }

    @Test
    void 주문상품이_주어진상품과_같으면_true_를_반환한다() {
        final Product product = getProduct(1L, 1000);
        final OrderItem orderItem = getOrderItemRequest(getCartProductRequest(product, 10));
        assertThat(orderItem.isThisProduct(product)).isTrue();
    }

    @Test
    void 주문상품이_주어진상품과_다르면_false_를_반환한다() {
        final Product product = getProduct(1L, 1000);
        final OrderItem orderItem = getOrderItemRequest(getCartProductRequest(getProduct(2L, 1000), 10));
        assertThat(orderItem.isThisProduct(product)).isFalse();
    }
}
