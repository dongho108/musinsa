package com.musinsa.domain;

import java.util.LinkedList;
import java.util.List;

public class Cart {

    private Long id;
    private List<CartProduct> cartProducts;

    private Cart(final Long id, final List<CartProduct> cartProducts) {
        this.id = id;
        this.cartProducts = cartProducts;
    }

    public static Cart of(final Long id, final List<CartProduct> cartProducts) {
        validateCartProducts(cartProducts);
        return new Cart(id, cartProducts);
    }

    public static Cart createEmptyCart() {
        return new Cart(null, new LinkedList<>());
    }

    public static Cart createForEntity(final Long id, final List<CartProduct> cartProducts) {
        return new Cart(id, cartProducts);
    }

    private static void validateCartProducts(final List<CartProduct> cartProducts) {
        if (cartProducts == null) {
            throw new IllegalArgumentException("장바구니 상품들이 null 이면 장바구니 상품을 생성할 수 없습니다.");
        }

        if (cartProducts.size() == 0) {
            throw new IllegalArgumentException("장바구니 상품들이 하나도 없으면 장바구니 상품을 생성할 수 없습니다.");
        }
    }

    public void add(final CartProduct cartProduct) {
        final List<CartProduct> cartProducts = new LinkedList<>(this.cartProducts);
        cartProducts.add(cartProduct);
        this.cartProducts = cartProducts;
    }

    public void clear() {
        this.cartProducts = new LinkedList<>();
    }

    public Long getId() {
        return id;
    }

    public List<CartProduct> getCartProducts() {
        return cartProducts;
    }
}
