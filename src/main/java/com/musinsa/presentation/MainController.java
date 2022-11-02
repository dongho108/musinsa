package com.musinsa.presentation;

import com.musinsa.application.CartService;
import com.musinsa.presentation.handler.Handler;
import com.musinsa.presentation.handler.HandlerMapper;
import com.musinsa.presentation.view.InputView;
import com.musinsa.presentation.view.OutputView;
import org.springframework.stereotype.Controller;

@Controller
public class MainController {

    private final ProductInitializer productInitializer;
    private final CartService cartService;
    private final HandlerMapper handlerMapper;

    public MainController(final ProductInitializer productInitializer,
                          final CartService cartService,
                          final HandlerMapper handlerMapper) {
        this.productInitializer = productInitializer;
        this.cartService = cartService;
        this.handlerMapper = handlerMapper;
    }

    public void init() {
        productInitializer.initData();
        cartService.save();
    }

    public void run() {
        while (true) {
            final String command = InputView.printCommandInformation();
            try {
                final Handler handler = handlerMapper.getHandler(command);
                final boolean isContinue = handler.execute();
                if (!isContinue) {
                    break;
                }
            } catch (Exception exception) {
                OutputView.printErrorMessage(exception.getMessage());
            }
        }
    }
}
