package com.musinsa.dao;

import com.musinsa.domain.Product;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Repository;

@Repository
public class ProductStore implements ProductDao {

    private final AtomicLong id = new AtomicLong(0);
    private final Map<Long, Product> store = new ConcurrentHashMap<>();

    @Override
    public Product save(final Product entity) {
        final Product product = new Product(id.addAndGet(1),
                entity.getSerialNumber(),
                entity.getName(),
                entity.getPrice(),
                entity.getStock());
        store.put(id.get(), product);
        return product;
    }

    @Override
    public Optional<Product> findById(final Long id) {
        if (store.containsKey(id)) {
            return Optional.of(store.get(id));
        }
        return Optional.empty();
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public Optional<Product> findByProductSerialNumber(final String serialNumber) {
        return findAll().stream()
                .filter(it -> it.getSerialNumber().equals(serialNumber))
                .findFirst();
    }

    @Override
    public void clear() {
        id.set(0);
        store.clear();
    }
}
