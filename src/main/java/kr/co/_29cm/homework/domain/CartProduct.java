package kr.co._29cm.homework.domain;

import java.math.BigDecimal;

public class CartProduct {

    private Long id;
    private Integer quantity;
    private Long productId;
    private String name;
    private BigDecimal price;

    private CartProduct(final Long id,
                        final Integer quantity,
                        final Long productId,
                        final String name,
                        final BigDecimal price) {
        this.id = id;
        this.quantity = quantity;
        this.productId = productId;
        this.name = name;
        this.price = price;
    }

    public CartProduct(final Integer quantity,
                       final Long productId,
                       final String name,
                       final BigDecimal price) {
        this(null, quantity, productId, name, price);
    }

    public static CartProduct of(final Product product, final Integer quantity) {
        return new CartProduct(quantity, product.getId(), product.getName(), product.getPrice());
    }

    public Long getId() {
        return id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Long getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
