package kr.co._29cm.homework.dao;

import kr.co._29cm.homework.domain.Product;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
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
    public Optional<Product> findBySerialNumber(final String serialNumber) {
        return findAll().stream()
                .filter(it -> it.getSerialNumber().equals(serialNumber))
                .findFirst();
    }

    @Override
    public Boolean existsBySerialNumber(final String serialNumber) {
        return store.values().stream()
                .anyMatch(it -> it.getSerialNumber().equals(serialNumber));
    }

    @Override
    public List<Product> findAllByIds(final List<Long> productIds) {
        return productIds.stream()
                .map(this::findById)
                .map(it -> it.orElseThrow(() -> new NoSuchElementException("해당 상품이 존재하지 않습니다.")))
                .collect(Collectors.toList());
    }

    @Override
    public void clear() {
        id.set(0);
        store.clear();
    }
}
