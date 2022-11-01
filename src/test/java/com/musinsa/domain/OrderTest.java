package com.musinsa.domain;

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

    private Order getOrder(final int price) {
        final CartProduct 무탠다드_반팔 = new CartProduct(1L, 1, 1L, "무탠다드 반팔", BigDecimal.valueOf(price));
        final OrderItems orderItems = OrderItems.from(List.of(무탠다드_반팔));
        final Order order = new Order(orderItems);
        return order;
    }
}
