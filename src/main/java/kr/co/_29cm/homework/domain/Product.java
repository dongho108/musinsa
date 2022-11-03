package kr.co._29cm.homework.domain;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicInteger;

public class Product {

    private Long id;
    private String serialNumber;
    private String name;
    private BigDecimal price;
    private AtomicInteger stock;

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
        this.stock = new AtomicInteger(stock);
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

    public synchronized void reduceStock(final Integer quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("수량은 음수일 수 없습니다.");
        }

        validateQuantity(quantity);
        this.stock.set(this.stock.get() - quantity);
    }

    private void validateQuantity(final Integer quantity) {
        if (this.stock.get() < quantity) {
            throw new SoldOutException();
        }
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
        return stock.get();
    }
}
