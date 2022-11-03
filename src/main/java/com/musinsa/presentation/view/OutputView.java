package com.musinsa.presentation.view;

import com.musinsa.application.dto.OrderItemResponse;
import com.musinsa.application.dto.OrderResponse;
import com.musinsa.application.dto.ProductResponse;
import java.util.List;

public class OutputView {

    private static final String PRODUCT_SERIAL_NUMBER = "상품번호" + " ".repeat(3);
    private static final String PRODUCT_NAME = "상품명" + " ".repeat(25);
    private static final String PRODUCT_PRICE = "판매가격" + " ".repeat(3);
    private static final String PRODUCT_STOCK = "재고수" + " ".repeat(3);
    private static final String NEW_LINE = System.lineSeparator();
    private static final String BOUNDARY_LINE = "-".repeat(50) + NEW_LINE;
    private static final String HEAD = String.join("",
            PRODUCT_SERIAL_NUMBER,
            PRODUCT_NAME,
            PRODUCT_PRICE,
            PRODUCT_STOCK,
            NEW_LINE);

    public static void printProducts(final List<ProductResponse> products) {
        final StringBuilder stringBuilder = new StringBuilder(HEAD);
        products.forEach(it -> stringBuilder.append(
                String.join(" ".repeat(3),
                        it.getSerialNumber(),
                        it.getName(),
                        String.valueOf(it.getPrice()),
                        String.valueOf(it.getStock()))
        ).append(NEW_LINE));
        System.out.println(stringBuilder);
    }

    public static void printOrderItems(final OrderResponse orderResponse) {
        final StringBuilder stringBuilder = new StringBuilder(BOUNDARY_LINE);
        orderResponse.getOrderItemsResponse().forEach(it -> appendOrderItems(stringBuilder, it));
        appendAmounts(orderResponse, stringBuilder);
        System.out.println(stringBuilder);
    }

    private static StringBuilder appendOrderItems(final StringBuilder stringBuilder,
                                                  final OrderItemResponse orderItemResponse) {
        return stringBuilder.append(orderItemResponse.getName())
                .append(" - ").append(orderItemResponse.getQuantity()).append("개")
                .append(NEW_LINE);
    }

    private static void appendAmounts(final OrderResponse orderResponse,
                                      final StringBuilder stringBuilder) {
        appendAmount(orderResponse.getOrderAmount(), stringBuilder, "주문금액: ");
        appendAmount(orderResponse.getPaymentAmount(), stringBuilder, "지불금액: ");
        stringBuilder.append(BOUNDARY_LINE);
    }

    private static void appendAmount(final String amount,
                                     final StringBuilder stringBuilder,
                                     final String message) {
        stringBuilder.append(BOUNDARY_LINE);
        stringBuilder.append(message)
                .append(convertMoneyNotation(amount))
                .append(NEW_LINE);
    }

    private static String convertMoneyNotation(final String money) {
        return String.format("%,d", Integer.parseInt(money));
    }

    public static void printErrorMessage(final String message) {
        System.out.println(message);
    }
}
