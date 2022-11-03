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

    public static Cart createEmptyCart() {
        return new Cart(null, new LinkedList<>());
    }

    public static Cart createForEntity(final Long id, final List<CartProduct> cartProducts) {
        return new Cart(id, cartProducts);
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
