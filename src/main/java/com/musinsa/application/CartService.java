package com.musinsa.application;

import com.musinsa.application.dto.CartRequest;
import com.musinsa.application.dto.CartResponse;
import com.musinsa.dao.CartStore;
import com.musinsa.dao.ProductStore;
import com.musinsa.domain.Cart;
import com.musinsa.domain.CartProduct;
import com.musinsa.domain.Product;
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
        return productStore.findByProductSerialNumber(serialNumber)
                .orElseThrow(() -> new NoSuchElementException("상품번호에 해당하는 상품이 존재하지 않습니다."));
    }

    private Cart getCartById(final Long id) {
        return cartStore.findById(id)
                .orElseThrow(() -> new NoSuchElementException("해당하는 cart 가 존재하지 않습니다."));
    }
}
