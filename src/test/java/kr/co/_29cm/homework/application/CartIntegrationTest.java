package kr.co._29cm.homework.application;

import static org.assertj.core.api.Assertions.assertThat;

import kr.co._29cm.homework.application.dto.CartRequest;
import kr.co._29cm.homework.application.dto.CartResponse;
import kr.co._29cm.homework.application.dto.ProductRequest;
import kr.co._29cm.homework.application.dto.ProductResponse;
import kr.co._29cm.homework.dao.CartDao;
import kr.co._29cm.homework.dao.ProductDao;
import kr.co._29cm.homework.support.test.ServiceTest;
import java.math.BigDecimal;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@ServiceTest
class CartIntegrationTest {

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private CartDao cartDao;

    @AfterEach
    void cleanUp() {
        productDao.clear();
        cartDao.clear();
    }

    @Test
    void 장바구니를_생성한다() {
        final CartResponse cartResponse = cartService.create();
        assertThat(cartResponse.getId()).isNotNull();
    }

    @Test
    void 장바구니에_상품을_추가한다() {
        final ProductResponse productResponse = 상품_추가();
        final CartResponse cartResponse = 장바구니_생성();
        final CartResponse addCartResponse = cartService.add(CartRequest.of(cartResponse.getId(), productResponse.getSerialNumber(), "5"));
        assertThat(addCartResponse.getCartProductsResponse()).hasSize(1);
    }

    private CartResponse 장바구니_생성() {
        return cartService.create();
    }

    private ProductResponse 상품_추가() {
        return productService.create(
                new ProductRequest("1234", "29CM", BigDecimal.valueOf(1000), 10));
    }
}
