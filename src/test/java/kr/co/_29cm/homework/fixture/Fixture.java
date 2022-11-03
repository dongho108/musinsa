package kr.co._29cm.homework.fixture;

import kr.co._29cm.homework.domain.Cart;
import kr.co._29cm.homework.domain.CartProduct;
import kr.co._29cm.homework.domain.Order;
import kr.co._29cm.homework.domain.OrderItem;
import kr.co._29cm.homework.domain.Product;
import java.math.BigDecimal;
import java.util.List;

public class Fixture {

    public static Cart getCart(final Long id, final List<CartProduct> cartProducts) {
        return Cart.createForEntity(id, cartProducts);
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

    public static Product getProduct(final Long id, final Integer price, final Integer stock) {
        return getProduct(id, "111", "29CM", price, stock);
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
        return getOrderRequest(getCart(1L, List.of(getCartProductRequest(getProduct(), 1))));
    }

    public static Order getOrderRequest(final Cart cart) {
        return Order.from(cart);
    }

    public static OrderItem getOrderItemRequest(final CartProduct cartProduct) {
        return OrderItem.of(cartProduct);
    }
}
