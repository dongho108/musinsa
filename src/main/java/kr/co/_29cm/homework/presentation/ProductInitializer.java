package kr.co._29cm.homework.presentation;

import kr.co._29cm.homework.application.ProductService;
import kr.co._29cm.homework.application.dto.ProductRequest;
import kr.co._29cm.homework.support.InputParser;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ProductInitializer {

    private static final String DATA_SOURCE_URL = "data/items.csv";

    private final ProductService productService;
    private final DataLoader dataLoader;

    public ProductInitializer(final ProductService productService, final DataLoader dataLoader) {
        this.productService = productService;
        this.dataLoader = dataLoader;
    }

    public void initData() {
        final List<String> lines = dataLoader.read(DATA_SOURCE_URL);
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
