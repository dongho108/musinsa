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
        validateDuplicate(productRequest);
        final Product product = new Product(
                productRequest.getSerialNumber(),
                productRequest.getName(),
                productRequest.getPrice(),
                productRequest.getStock()
        );

        return ProductResponse.of(productDao.save(product));
    }

    private void validateDuplicate(final ProductRequest productRequest) {
        if (productDao.existsBySerialNumber(productRequest.getSerialNumber())) {
            throw new IllegalArgumentException("동일한 상품번호의 상품이 존재합니다.");
        }
    }

    public List<ProductResponse> findAll() {
        return productDao.findAll().stream()
                .map(ProductResponse::of)
                .collect(Collectors.toList());
    }
}
