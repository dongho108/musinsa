package com.musinsa.domain;

import java.math.BigDecimal;

public class Product {

    private Long id;
    private String serialNumber;
    private String name;
    private BigDecimal price;
    private Integer stock;

    public Product(final String serialNumber, final String name, final BigDecimal price, final Integer stock) {
        validatePrice(price);
        validateStock(stock);
        this.serialNumber = serialNumber;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    private void validateStock(final Integer stock) {
        if (stock < 0) {
            throw new IllegalArgumentException("상품의 가격이 음수이면 상품을 생성할 수 없습니다.");
        }
    }

    private void validatePrice(final BigDecimal price) {
        if (price.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("상품의 가격이 음수이면 상품을 생성할 수 없습니다.");
        }
    }
}
