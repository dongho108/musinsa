package com.musinsa.presentation;

import com.musinsa.application.CartService;
import com.musinsa.application.OrderService;
import com.musinsa.application.ProductService;
import com.musinsa.application.dto.CartRequest;
import com.musinsa.application.dto.OrderRequest;
import com.musinsa.application.dto.OrderResponse;
import com.musinsa.presentation.view.InputView;
import com.musinsa.presentation.view.OutputView;
import org.springframework.stereotype.Controller;

@Controller
public class MainController {

    private static final Long CART_ID = 1L;

    private final ProductInitializer productInitializer;
    private final ProductService productService;
    private final CartService cartService;
    private final OrderService orderService;

    public MainController(final ProductInitializer productInitializer, final ProductService productService,
                          final CartService cartService, final OrderService orderService) {
        this.productInitializer = productInitializer;
        this.productService = productService;
        this.cartService = cartService;
        this.orderService = orderService;
    }

    public void init() {
        productInitializer.initData();
        cartService.save();
    }

    public void run() {
        while (true) {
            final String command = InputView.printCommandInformation();
            if (command.equals("q") || command.equals("quit")) {
                break;
            }
            if (command.equals("o")) {
                OutputView.printProducts(productService.findAll());
                while (true) {
                    final String serialNumber = InputView.printSerialNumberInputMessage();
                    final String quantity = InputView.printQuantityInputMessage();
                    if (serialNumber.equals(" ") && quantity.equals(" ")) {
                        break;
                    }
                    cartService.add(CartRequest.of(CART_ID, serialNumber, quantity));
                }
            }
            final OrderResponse orderResponse = orderService.create(new OrderRequest(CART_ID));
            OutputView.printOrderItems(orderResponse);
        }
    }
}
