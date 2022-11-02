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
        validateNumberFormat(serialNumber, "상품 번호를 정확히 입력해 주세요.");
        validateNumberFormat(quantity, "수량을 제대로 입력해 주세요.");
        return new CartRequest(cartId, serialNumber, Integer.parseInt(quantity));
    }

    private static void validateNumberFormat(final String number, final String message) {
        try {
            Integer.parseInt(number);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(message);
        }
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
