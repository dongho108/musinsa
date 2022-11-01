package com.musinsa.application.dto;

public class CartRequest {

    private final Long cartId;
    private final String serialNumber;
    private final Integer quantity;

    public CartRequest(final Long cartId, final String serialNumber, final Integer quantity) {
        this.cartId = cartId;
        this.serialNumber = serialNumber;
        this.quantity = quantity;
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
