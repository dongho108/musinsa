package kr.co._29cm.homework.domain;

import static kr.co._29cm.homework.fixture.Fixture.getCartProductRequest;
import static kr.co._29cm.homework.fixture.Fixture.getProduct;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.Test;

class OrderItemsTest {

    @Test
    void 주문상품목록의_금액을_계산한다() {
        final CartProduct 반팔 = getCartProductRequest(getProduct(10000), 1);
        final CartProduct 긴팔 = getCartProductRequest(getProduct(20000), 2);

        final OrderItems orderItems = OrderItems.from(List.of(반팔, 긴팔));
        assertThat(orderItems.calculateAmount()).isEqualTo(BigDecimal.valueOf(50000));
    }

    @Test
    void 장바구니상품들이_null_이면_생성할_수_없다() {
        assertThatThrownBy(() -> OrderItems.from(null))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 장바구니상품들의_크기가_0이면_생성할_수_없다() {
        assertThatThrownBy(() -> OrderItems.from(List.of()))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 주문목록상품들의_실제재고량을_감소시킨다() {
        final Product product1 = getProduct(1L, 10000, 10);
        final Product product2 = getProduct(2L, 20000, 10);
        final CartProduct 반팔 = getCartProductRequest(product1, 1);
        final CartProduct 긴팔 = getCartProductRequest(product2, 2);

        final OrderItems orderItems = OrderItems.from(List.of(반팔, 긴팔));
        orderItems.reduceStock(List.of(product1, product2));

        assertAll(
                () -> assertThat(product1.getStock()).isEqualTo(9),
                () -> assertThat(product2.getStock()).isEqualTo(8)
        );
    }
}
