package kr.co._29cm.homework.application;

import kr.co._29cm.homework.application.dto.OrderRequest;
import kr.co._29cm.homework.application.dto.OrderResponse;
import kr.co._29cm.homework.dao.CartDao;
import kr.co._29cm.homework.dao.OrderDao;
import kr.co._29cm.homework.dao.ProductDao;
import kr.co._29cm.homework.domain.Cart;
import kr.co._29cm.homework.domain.CartProduct;
import kr.co._29cm.homework.domain.Order;
import kr.co._29cm.homework.domain.Product;
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
