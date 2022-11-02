package com.musinsa.application.dto;

import com.musinsa.domain.Order;
import com.musinsa.domain.OrderItems;
import java.util.List;
import java.util.stream.Collectors;

public class OrderResponse {

    private final String orderAmount;
    private final String paymentAmount;
    private final List<OrderItemResponse> orderItemsResponse;

    public OrderResponse(final String orderAmount,
                         final String paymentAmount,
                         final List<OrderItemResponse> orderItemsResponse) {
        this.orderAmount = orderAmount;
        this.paymentAmount = paymentAmount;
        this.orderItemsResponse = orderItemsResponse;
    }

    public static OrderResponse from(final Order order) {
        return new OrderResponse(String.valueOf(order.calculateAmount()),
                String.valueOf(order.calculatePaymentAmount()),
                mapToOrderItemResponse(order.getOrderItems()));
    }

    private static List<OrderItemResponse> mapToOrderItemResponse(final OrderItems orderItems) {
        return orderItems.getOrderItems().stream()
                .map(OrderItemResponse::from)
                .collect(Collectors.toList());
    }

    public String getOrderAmount() {
        return orderAmount;
    }

    public String getPaymentAmount() {
        return paymentAmount;
    }

    public List<OrderItemResponse> getOrderItemsResponse() {
        return orderItemsResponse;
    }
}
