package com.musinsa.dao;

import com.musinsa.domain.Product;
import java.util.List;
import java.util.Optional;

public interface ProductDao extends Store<Product> {

    Optional<Product> findBySerialNumber(String serialNumber);

    Boolean existsBySerialNumber(String serialNumber);

    List<Product> findAllByIds(List<Long> productIds);
}
