package com.musinsa.domain;

import java.util.List;

public class Cart {

    private Long id;
    private List<CartProduct> cartProducts;

    public Cart(final Long id, final List<CartProduct> cartProducts) {
        validateCartProducts(cartProducts);
        this.id = id;
        this.cartProducts = cartProducts;
    }

    private void validateCartProducts(final List<CartProduct> cartProducts) {
        if (cartProducts == null) {
            throw new IllegalArgumentException("장바구니 상품들이 null 이면 장바구니 상품을 생성할 수 없습니다.");
        }

        if (cartProducts.size() == 0) {
            throw new IllegalArgumentException("장바구니 상품들의 size가 0 이면 장바구니 상품을 생성할 수 없습니다.");
        }
    }
}
