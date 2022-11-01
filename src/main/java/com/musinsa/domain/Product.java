package com.musinsa.domain;

import java.math.BigDecimal;

public class Product {

    private Long id;
    private String serialNumber;
    private String name;
    private BigDecimal price;
    private Integer stock;

    public Product(final Long id,
                   final String serialNumber,
                   final String name,
                   final BigDecimal price,
                   final Integer stock) {
        validatePrice(price);
        validateStock(stock);
        this.id = id;
        this.serialNumber = serialNumber;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public Product(final String serialNumber, final String name, final BigDecimal price, final Integer stock) {
        this(null, serialNumber, name, price, stock);
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

    public void reduceStock(final Integer quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("수량은 음수일 수 없습니다.");
        }
        if (this.stock < quantity) {
            throw new SoldOutException();
        }
        this.stock -= quantity;
    }

    public Long getId() {
        return id;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Integer getStock() {
        return stock;
    }
}
