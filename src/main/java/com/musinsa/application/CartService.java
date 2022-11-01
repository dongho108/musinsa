package com.musinsa.application;

import com.musinsa.application.dto.CartRequest;
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

    public Cart add(final CartRequest cartRequest) {
        final Product product = getProductBySerialNumber(cartRequest.getSerialNumber());
        final Cart cart = cartStore.findById(cartRequest.getCartId());
        cart.add(CartProduct.of(product, cartRequest.getQuantity()));
        return cartStore.save(cart);
    }

    private Product getProductBySerialNumber(final String serialNumber) {
        return productStore.findByProductSerialNumber(serialNumber)
                .orElseThrow(NoSuchElementException::new);
    }
}
