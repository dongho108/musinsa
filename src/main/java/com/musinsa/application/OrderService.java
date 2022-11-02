package com.musinsa.application;

import com.musinsa.application.dto.OrderRequest;
import com.musinsa.application.dto.OrderResponse;
import com.musinsa.dao.CartDao;
import com.musinsa.dao.OrderDao;
import com.musinsa.domain.Cart;
import com.musinsa.domain.CartProduct;
import com.musinsa.domain.Order;
import com.musinsa.domain.OrderItems;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderDao orderDao;
    private final CartDao cartDao;

    public OrderService(final OrderDao orderDao, final CartDao cartDao) {
        this.orderDao = orderDao;
        this.cartDao = cartDao;
    }

    public OrderResponse create(final OrderRequest orderRequest) {
        final Long cartId = orderRequest.getCartId();
        final Cart cart = cartDao.findById(cartId);
        final List<CartProduct> cartProducts = cart.getCartProducts();
        final Order order = new Order(OrderItems.from(cartProducts));
        return OrderResponse.from(orderDao.save(order));
    }
}
