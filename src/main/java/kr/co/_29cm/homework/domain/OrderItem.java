package kr.co._29cm.homework.domain;

import java.math.BigDecimal;

public class OrderItem {

    private Long id;
    private Long productId;
    private String name;
    private BigDecimal price;
    private Integer quantity;

    public OrderItem(final Long id, final Long productId, final String name, final BigDecimal price,
                     final Integer quantity) {
        this.id = id;
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public static OrderItem of(final CartProduct cartProduct) {
        return new OrderItem(null, cartProduct.getProductId(), cartProduct.getName(), cartProduct.getPrice(), cartProduct.getQuantity());
    }

    public Boolean isThisProduct(final Product product) {
        return productId.equals(product.getId());
    }

    public String getName() {
        return name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public BigDecimal getAmount() {
        return price.multiply(BigDecimal.valueOf(quantity));
    }
}
