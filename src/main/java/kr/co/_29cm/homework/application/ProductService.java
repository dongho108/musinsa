package kr.co._29cm.homework.application;

import kr.co._29cm.homework.application.dto.ProductRequest;
import kr.co._29cm.homework.application.dto.ProductResponse;
import kr.co._29cm.homework.dao.ProductDao;
import kr.co._29cm.homework.domain.Product;
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
