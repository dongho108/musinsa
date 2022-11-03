package kr.co._29cm.homework.application.dto;

import kr.co._29cm.homework.domain.CartProduct;
import java.math.BigDecimal;

public class CartProductResponse {

    private final String name;
    private final BigDecimal price;
    private final Integer quantity;

    private CartProductResponse(final String name, final BigDecimal price, final Integer quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public static CartProductResponse from(final CartProduct cartProduct) {
        return new CartProductResponse(cartProduct.getName(),
                cartProduct.getPrice(),
                cartProduct.getQuantity());
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Integer getQuantity() {
        return quantity;
    }
}
