package com.musinsa.dao;

import com.musinsa.domain.Cart;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Repository;

@Repository
public class CartStore implements CartDao{

    private final AtomicLong id = new AtomicLong(0);
    private final Map<Long, Cart> store = new ConcurrentHashMap<>();

    @Override
    public Cart save(final Cart entity) {
        if (Objects.nonNull(entity.getId())) {
            store.replace(entity.getId(), entity);
            return entity;
        }
        final Cart cart = Cart.createForEntity(id.addAndGet(1), entity.getCartProducts());
        store.put(id.get(), cart);
        return cart;
    }

    @Override
    public Cart findById(final Long id) {
        return store.get(id);
    }

    @Override
    public List<Cart> findAll() {
        return new ArrayList<>(store.values());
    }
}
