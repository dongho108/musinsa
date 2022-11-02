package com.musinsa.application.dto;

import com.musinsa.domain.Product;
import java.math.BigDecimal;

public class ProductResponse {

    private String serialNumber;
    private String name;
    private BigDecimal price;
    private Integer stock;

    private ProductResponse(final String serialNumber,
                           final String name,
                           final BigDecimal price,
                           final Integer stock) {
        this.serialNumber = serialNumber;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public static ProductResponse of(final Product product) {
        return new ProductResponse(
                product.getSerialNumber(),
                product.getName(),
                product.getPrice(),
                product.getStock()
        );
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
