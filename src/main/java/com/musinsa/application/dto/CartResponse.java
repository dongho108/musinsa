package com.musinsa.application.dto;

import com.musinsa.domain.Cart;
import java.util.List;
import java.util.stream.Collectors;

public class CartResponse {

    private final Long id;
    private final List<CartProductResponse> cartProductsResponse;

    private CartResponse(final Long id, final List<CartProductResponse> cartProductsResponse) {
        this.id = id;
        this.cartProductsResponse = cartProductsResponse;
    }

    public static CartResponse from(final Cart cart) {
        return new CartResponse(cart.getId(), mapToCartProductResponses(cart));
    }

    private static List<CartProductResponse> mapToCartProductResponses(final Cart cart) {
        return cart.getCartProducts().stream()
                .map(CartProductResponse::from)
                .collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public List<CartProductResponse> getCartProductsResponse() {
        return cartProductsResponse;
    }
}
