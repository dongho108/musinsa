package com.musinsa.dao;

import com.musinsa.domain.Order;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Repository;

@Repository
public class OrderStore implements OrderDao {

    private final AtomicLong id = new AtomicLong(0);
    private final Map<Long, Order> store = new ConcurrentHashMap<>();

    @Override
    public Order save(final Order entity) {
        final Order order = new Order(
                id.addAndGet(1),
                entity.getOrderItems()
        );
        store.put(id.get(), order);
        return order;
    }

    @Override
    public Optional<Order> findById(final Long id) {
        if (store.containsKey(id)) {
            return Optional.of(store.get(id));
        }
        return Optional.empty();
    }

    @Override
    public List<Order> findAll() {
        return new ArrayList<>(store.values());
    }
}
