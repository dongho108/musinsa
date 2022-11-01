package com.musinsa.dao;

import com.musinsa.domain.Product;
import java.util.Optional;

public interface ProductDao extends Store<Product> {

    Optional<Product> findByProductSerialNumber(String serialNumber);
}
