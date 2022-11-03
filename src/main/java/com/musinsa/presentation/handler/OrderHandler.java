package com.musinsa.presentation.handler;

import com.musinsa.application.CartService;
import com.musinsa.application.OrderService;
import com.musinsa.application.ProductService;
import com.musinsa.application.dto.CartRequest;
import com.musinsa.application.dto.OrderRequest;
import com.musinsa.application.dto.OrderResponse;
import com.musinsa.presentation.view.InputView;
import com.musinsa.presentation.view.OutputView;

public class OrderHandler implements Handler{

    private static final Long CART_ID = 1L;

    private final ProductService productService;
    private final CartService cartService;
    private final OrderService orderService;

    public OrderHandler(final ProductService productService,
                        final CartService cartService,
                        final OrderService orderService) {
        this.productService = productService;
        this.cartService = cartService;
        this.orderService = orderService;
    }

    @Override
    public boolean isSupport(final String command) {
        return command.equals("o") || command.equals("order");
    }

    @Override
    public boolean execute() {
        OutputView.printProducts(productService.findAll());
        startShopping();
        final OrderResponse orderResponse = orderService.create(new OrderRequest(CART_ID));
        OutputView.printOrderItems(orderResponse);
        return true;
    }

    private void startShopping() {
        while (true) {
            final String serialNumber = InputView.printSerialNumberInputMessage();
            final String quantity = InputView.printQuantityInputMessage();
            if (isOrderInput(serialNumber, quantity)) {
                break;
            }
            addProductToCart(serialNumber, quantity);
        }
    }

    private boolean isOrderInput(final String serialNumber, final String quantity) {
        return serialNumber.equals(" ") && quantity.equals(" ");
    }

    private void addProductToCart(final String serialNumber, final String quantity) {
        try {
            cartService.add(CartRequest.of(CART_ID, serialNumber, quantity));
        } catch (Exception exception) {
            OutputView.printErrorMessage(exception.getMessage());
        }
    }
}
