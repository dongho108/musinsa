package com.musinsa.domain;

import static com.musinsa.fixture.Fixture.getCart;
import static com.musinsa.fixture.Fixture.getCartProductRequest;
import static com.musinsa.fixture.Fixture.getProduct;
import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class OrderTest {

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 100, 10000, 49999})
    void 주문금액이_50000원_미만일_경우_지불금액에_배달비가_포함된다(int price) {
        final Order order = getOrder(price);
        assertThat(order.calculatePaymentAmount()).isEqualTo(BigDecimal.valueOf(price + 2500));
    }

    @ParameterizedTest
    @ValueSource(ints = {50000, 50001, 100_000})
    void 주문금액이_50000원_이하일_경우_지불금액에_배달비가_포함되지않는다(int price) {
        final Order order = getOrder(price);
        assertThat(order.calculatePaymentAmount()).isEqualTo(BigDecimal.valueOf(price));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 100, 10000, 49999, 50000, 50001, 100_000})
    void 주문금액은_배달비를_포함하지_않은_주문상품목록의_가격의_합이다(int price) {
        final Order order = getOrder(price);
        assertThat(order.calculateAmount()).isEqualTo(BigDecimal.valueOf(price));
    }

    private Order getOrder(final int price) {
        final Cart cart = getCart(1L, List.of(getCartProductRequest(getProduct(price), 1)));
        return Order.from(cart);
    }
}
