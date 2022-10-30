package com.musinsa.application;

import com.musinsa.application.dto.ProductRequest;
import com.musinsa.dao.ProductDao;
import com.musinsa.domain.Product;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductDao productDao;

    public ProductService(final ProductDao productDao) {
        this.productDao = productDao;
    }

    public Product create(final ProductRequest productRequest) {
        return productDao.save(new Product(
                productRequest.getSerialNumber(),
                productRequest.getName(),
                productRequest.getPrice(),
                productRequest.getStock()
        ));
    }

    public List<Product> findAll() {
        return productDao.findAll();
    }
}
