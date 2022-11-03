package kr.co._29cm.homework.application.dto;

import java.math.BigDecimal;

public class ProductRequest {
    private final String serialNumber;
    private final String name;
    private final BigDecimal price;
    private final Integer stock;

    public ProductRequest(final String serialNumber, final String name, final BigDecimal price, final Integer stock) {
        this.serialNumber = serialNumber;
        this.name = name;
        this.price = price;
        this.stock = stock;
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
