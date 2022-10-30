package com.musinsa.presentation;

import com.musinsa.application.ProductService;
import com.musinsa.application.dto.ProductRequest;
import com.musinsa.support.InputParser;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ProductInitializer {

    private final ProductService productService;
    private final DataLoader dataLoader;

    public ProductInitializer(final ProductService productService, final DataLoader dataLoader) {
        this.productService = productService;
        this.dataLoader = dataLoader;
    }

    public void initData() {
        final List<String> lines = dataLoader.read("data/items.csv");
        final List<List<String>> data = InputParser.parse(lines);

        for (int rowNum = 1; rowNum < data.size(); rowNum++) {
            final List<String> row = data.get(rowNum);
            final ProductRequest productRequest = new ProductRequest(row.get(0),
                    row.get(1),
                    BigDecimal.valueOf(Integer.parseInt(row.get(2))),
                    Integer.parseInt(row.get(3)));
            productService.create(productRequest);
        }
    }
}
