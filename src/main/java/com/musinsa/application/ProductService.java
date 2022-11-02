package com.musinsa.application;

import com.musinsa.application.dto.ProductRequest;
import com.musinsa.application.dto.ProductResponse;
import com.musinsa.dao.ProductDao;
import com.musinsa.domain.Product;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductDao productDao;

    public ProductService(final ProductDao productDao) {
        this.productDao = productDao;
    }

    public ProductResponse create(final ProductRequest productRequest) {
        final Product t = new Product(
                productRequest.getSerialNumber(),
                productRequest.getName(),
                productRequest.getPrice(),
                productRequest.getStock()
        );
        final Product product = productDao.save(t);
        return ProductResponse.of(product);
    }

    public List<ProductResponse> findAll() {
        return productDao.findAll().stream()
                .map(ProductResponse::of)
                .collect(Collectors.toList());
    }
}
