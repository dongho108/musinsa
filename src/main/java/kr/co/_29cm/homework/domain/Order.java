package kr.co._29cm.homework.domain;

import java.math.BigDecimal;
import java.util.List;

public class Order {

    private static final BigDecimal MINIMUM_FREE_DELIVERY_AMOUNT = BigDecimal.valueOf(50000);
    private static final BigDecimal DELIVERY_AMOUNT = BigDecimal.valueOf(2500);

    private Long id;
    private OrderItems orderItems;

    public Order(final Long id, final OrderItems orderItems) {
        this.id = id;
        this.orderItems = orderItems;
    }

    public static Order from(final Cart cart) {
        final OrderItems orderItems = OrderItems.from(cart.getCartProducts());
        return new Order(null, orderItems);
    }

    public BigDecimal calculateAmount() {
        return orderItems.calculateAmount();
    }

    public BigDecimal calculatePaymentAmount() {
        final BigDecimal amount = calculateAmount();
        if (amount.compareTo(MINIMUM_FREE_DELIVERY_AMOUNT) < 0) {
            return amount.add(DELIVERY_AMOUNT);
        }
        return amount;
    }

    public void place(final Cart cart, final List<Product> products) {
        orderItems.reduceStock(products);
        cart.clear();
    }

    public Long getId() {
        return id;
    }

    public OrderItems getOrderItems() {
        return orderItems;
    }
}
