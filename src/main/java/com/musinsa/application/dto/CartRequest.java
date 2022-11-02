package com.musinsa.application.dto;

public class CartRequest {

    private final Long cartId;
    private final String serialNumber;
    private final Integer quantity;

    private CartRequest(final Long cartId, final String serialNumber, final Integer quantity) {
        this.cartId = cartId;
        this.serialNumber = serialNumber;
        this.quantity = quantity;
    }

    public static CartRequest of(final Long cartId, final String serialNumber, final String quantity) {
        // TODO: quantity 입력 검증 필요
        return new CartRequest(cartId, serialNumber, Integer.parseInt(quantity));
    }

    public Long getCartId() {
        return cartId;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public Integer getQuantity() {
        return quantity;
    }
}
