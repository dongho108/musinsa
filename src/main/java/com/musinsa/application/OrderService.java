package com.musinsa.application;

import com.musinsa.application.dto.OrderRequest;
import com.musinsa.application.dto.OrderResponse;
import com.musinsa.dao.CartDao;
import com.musinsa.dao.OrderDao;
import com.musinsa.domain.Cart;
import com.musinsa.domain.Order;
import java.util.NoSuchElementException;
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
        final Cart cart = cartDao.findById(cartId)
                .orElseThrow(() -> new NoSuchElementException("해당하는 cart 가 존재하지 않습니다."));
        final Order order = Order.from(cart);
        cartDao.save(cart);
        return OrderResponse.from(orderDao.save(order));
    }
}
