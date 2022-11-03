package com.musinsa.application;

import com.musinsa.application.dto.OrderRequest;
import com.musinsa.application.dto.OrderResponse;
import com.musinsa.dao.CartDao;
import com.musinsa.dao.OrderDao;
import com.musinsa.dao.ProductDao;
import com.musinsa.domain.Cart;
import com.musinsa.domain.CartProduct;
import com.musinsa.domain.Order;
import com.musinsa.domain.Product;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderDao orderDao;
    private final CartDao cartDao;
    private final ProductDao productDao;

    public OrderService(final OrderDao orderDao, final CartDao cartDao, final ProductDao productDao) {
        this.orderDao = orderDao;
        this.cartDao = cartDao;
        this.productDao = productDao;
    }

    public OrderResponse create(final OrderRequest orderRequest) {
        final Long cartId = orderRequest.getCartId();
        final Cart cart = getCart(cartId);
        final Order order = Order.from(cart);
        final List<Long> productIds = mapToProductionIds(cart);
        final List<Product> products = productDao.findAllByIds(productIds);
        order.place(cart, products);
        cartDao.save(cart);
        return OrderResponse.from(orderDao.save(order));
    }

    private Cart getCart(final Long cartId) {
        return cartDao.findById(cartId)
                .orElseThrow(() -> new NoSuchElementException("해당하는 cart 가 존재하지 않습니다."));
    }

    private List<Long> mapToProductionIds(final Cart cart) {
        return cart.getCartProducts().stream()
                .map(CartProduct::getProductId)
                .collect(Collectors.toList());
    }
}
