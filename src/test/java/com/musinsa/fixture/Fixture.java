package com.musinsa.fixture;

import com.musinsa.domain.Cart;
import com.musinsa.domain.CartProduct;
import com.musinsa.domain.Order;
import com.musinsa.domain.OrderItem;
import com.musinsa.domain.OrderItems;
import com.musinsa.domain.Product;
import java.math.BigDecimal;
import java.util.List;

public class Fixture {

    public static Cart getCart(final Long id, final List<CartProduct> cartProducts) {
        return new Cart(id, cartProducts);
    }

    public static CartProduct getCartProductRequest(final Product product, final Integer quantity) {
        return CartProduct.of(product, quantity);
    }

    public static Product getProduct() {
        return getProduct(1L, "111", "29CM", 1000, 10);
    }

    public static Product getProduct(final Long id, final Integer price) {
        return getProduct(id, "111", "29CM", price, 10);
    }

    public static Product getProduct(final Integer price) {
        return getProduct(1L, "111", "29CM", price, 10);
    }

    public static Product getProduct(final Long id,
                                     final String serialNumber,
                                     final String name,
                                     final Integer price,
                                     final Integer stock) {
        return new Product(id, serialNumber, name, BigDecimal.valueOf(price), stock);
    }

    public static Order getOrderRequest() {
        final Product product = getProduct();
        final CartProduct cartProductRequest = getCartProductRequest(product, 1);
        final OrderItems orderItems = OrderItems.from(List.of(cartProductRequest));
        return getOrderRequest(orderItems);
    }

    public static Order getOrderRequest(final OrderItems orderItems) {
        return new Order(orderItems);
    }

    public static OrderItem getOrderItemRequest(final CartProduct cartProduct) {
        return OrderItem.of(cartProduct);
    }
}
