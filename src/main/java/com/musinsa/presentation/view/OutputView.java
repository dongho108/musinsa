package com.musinsa.presentation.view;

import com.musinsa.application.dto.ProductResponse;
import java.util.List;

public class OutputView {

    private static final String PRODUCT_SERIAL_NUMBER = "상품번호" + " ".repeat(3);
    private static final String PRODUCT_NAME = "상품명" + " ".repeat(25);
    private static final String PRODUCT_PRICE = "판매가격" + " ".repeat(3);
    private static final String PRODUCT_STOCK = "재고수" + " ".repeat(3);
    private static final String NEW_LINE = System.lineSeparator();

    public static void printProducts(final List<ProductResponse> products) {
        final String head = String.join("",
                PRODUCT_SERIAL_NUMBER,
                PRODUCT_NAME,
                PRODUCT_PRICE,
                PRODUCT_STOCK,
                NEW_LINE);
        final StringBuilder stringBuilder = new StringBuilder(head);
        products.forEach(it -> stringBuilder.append(
                String.join(" ".repeat(3),
                        it.getSerialNumber(),
                        it.getName(),
                        String.valueOf(it.getPrice()),
                        String.valueOf(it.getStock()))
        ).append(NEW_LINE));
        System.out.println(stringBuilder);
    }

    public static void printCommandInformation() {
        System.out.println("입력(o[order]: 주문, q[quit]: 종료)");
    }
}
