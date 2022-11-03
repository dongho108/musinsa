package com.musinsa.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import com.musinsa.application.dto.CartRequest;
import com.musinsa.application.dto.OrderRequest;
import com.musinsa.application.dto.OrderResponse;
import com.musinsa.application.dto.ProductRequest;
import com.musinsa.dao.CartDao;
import com.musinsa.dao.OrderDao;
import com.musinsa.dao.ProductDao;
import com.musinsa.support.test.ServiceTest;
import java.math.BigDecimal;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@ServiceTest
class OrderIntegrationTest {

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private CartService cartService;

    private Long cartId;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private CartDao cartDao;

    @Autowired
    private OrderDao orderDao;

    @AfterEach
    void cleanUp() {
        productDao.clear();
        cartDao.clear();
        orderDao.clear();
    }

    @BeforeEach
    void setUp() {
        cartId = cartService.create().getId();
    }

    @Test
    void 주문을_생성한다() {
        // given
        productService.create(getProductRequest("1234", "29CM", 1000, 10));
        productService.create(getProductRequest("1235", "30CM", 1000, 10));
        cartService.add(CartRequest.of(cartId, "1234", "5"));
        cartService.add(CartRequest.of(cartId, "1235", "5"));

        // when
        final OrderResponse orderResponse = orderService.create(new OrderRequest(cartId));

        // then
        assertAll(
                () -> assertThat(orderResponse.getOrderAmount()).isEqualTo("10000"),
                () -> assertThat(orderResponse.getPaymentAmount()).isEqualTo("12500"),
                () -> assertThat(orderResponse.getOrderItemsResponse()).hasSize(2)
        );
    }

    private ProductRequest getProductRequest(final String serialNumber, final String name, final Integer price, final Integer stock) {
        return new ProductRequest(serialNumber, name, BigDecimal.valueOf(price), stock);
    }
}