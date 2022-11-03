package kr.co._29cm.homework.application;

import kr.co._29cm.homework.application.dto.CartRequest;
import kr.co._29cm.homework.application.dto.CartResponse;
import kr.co._29cm.homework.dao.CartStore;
import kr.co._29cm.homework.dao.ProductStore;
import kr.co._29cm.homework.domain.Cart;
import kr.co._29cm.homework.domain.CartProduct;
import kr.co._29cm.homework.domain.Product;
import java.util.NoSuchElementException;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    private final ProductStore productStore;
    private final CartStore cartStore;

    public CartService(final ProductStore productStore, final CartStore cartStore) {
        this.productStore = productStore;
        this.cartStore = cartStore;
    }

    public CartResponse create() {
        return CartResponse.from(cartStore.save(Cart.createEmptyCart()));
    }

    public CartResponse add(final CartRequest cartRequest) {
        final Product product = getProductBySerialNumber(cartRequest.getSerialNumber());
        final Cart cart = getCartById(cartRequest.getCartId());
        cart.add(CartProduct.of(product, cartRequest.getQuantity()));
        return CartResponse.from(cartStore.save(cart));
    }

    private Product getProductBySerialNumber(final String serialNumber) {
        return productStore.findBySerialNumber(serialNumber)
                .orElseThrow(() -> new NoSuchElementException("상품번호에 해당하는 상품이 존재하지 않습니다."));
    }

    private Cart getCartById(final Long id) {
        return cartStore.findById(id)
                .orElseThrow(() -> new NoSuchElementException("해당하는 cart 가 존재하지 않습니다."));
    }
}
