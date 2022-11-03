package com.musinsa.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import com.musinsa.application.dto.ProductRequest;
import com.musinsa.application.dto.ProductResponse;
import com.musinsa.dao.ProductDao;
import com.musinsa.support.test.ServiceTest;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@ServiceTest
class ProductIntegrationTest {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductDao productDao;

    @AfterEach
    void cleanUp() {
        productDao.clear();
    }

    @Test
    void 상품을_생성한다() {
        final ProductRequest productRequest = getProductRequest("1234", "29CM", 1000, 10);
        final ProductResponse productResponse = productService.create(productRequest);
        assertAll(
                () -> assertThat(productResponse.getSerialNumber()).isEqualTo(productRequest.getSerialNumber()),
                () -> assertThat(productResponse.getName()).isEqualTo(productRequest.getName()),
                () -> assertThat(productResponse.getPrice()).isEqualTo(productRequest.getPrice()),
                () -> assertThat(productResponse.getStock()).isEqualTo(productRequest.getStock())
        );
    }

    @Test
    void 상품목록을_조회한다() {
        // given
        상품_등록_4개();

        // when
        final List<ProductResponse> productsResponse = productService.findAll();

        // then
        assertThat(productsResponse).hasSize(4);
    }

    private ProductRequest getProductRequest(final String serialNumber, final String name, final Integer price, final Integer stock) {
        return new ProductRequest(serialNumber, name, BigDecimal.valueOf(price), stock);
    }

    private void 상품_등록_4개() {
        productService.create(getProductRequest("1234", "29CM", 1000, 10));
        productService.create(getProductRequest("1235", "30CM", 1000, 10));
        productService.create(getProductRequest("1236", "31CM", 1000, 10));
        productService.create(getProductRequest("1237", "32CM", 1000, 10));
    }
}
