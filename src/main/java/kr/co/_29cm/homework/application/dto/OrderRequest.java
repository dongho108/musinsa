package kr.co._29cm.homework.application.dto;

public class OrderRequest {

    private final Long cartId;

    public OrderRequest(final Long cartId) {
        this.cartId = cartId;
    }

    public Long getCartId() {
        return cartId;
    }
}
