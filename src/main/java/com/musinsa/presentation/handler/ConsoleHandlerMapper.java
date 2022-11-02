package com.musinsa.presentation.handler;

import com.musinsa.application.CartService;
import com.musinsa.application.OrderService;
import com.musinsa.application.ProductService;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.stereotype.Component;

@Component
public class ConsoleHandlerMapper implements HandlerMapper {

    private final ProductService productService;
    private final CartService cartService;
    private final OrderService orderService;
    private final List<Handler> handlers;

    public ConsoleHandlerMapper(final ProductService productService,
                                final CartService cartService,
                                final OrderService orderService) {
        final ArrayList<Handler> handlers = getHandlers(productService, cartService, orderService);
        this.productService = productService;
        this.cartService = cartService;
        this.orderService = orderService;
        this.handlers = handlers;
    }

    private ArrayList<Handler> getHandlers(final ProductService productService,
                                           final CartService cartService,
                                         final OrderService orderService) {
        final ArrayList<Handler> handlers = new ArrayList<>(2);
        handlers.add(new QuitHandler());
        handlers.add(new OrderHandler(productService, cartService, orderService));
        return handlers;
    }


    @Override
    public Handler getHandler(final String command) {
        return handlers.stream()
                .filter(it -> it.isSupport(command))
                .findAny()
                .orElseThrow(() -> new NoSuchElementException("해당하는 명령이 없습니다. q 혹은 o 명령어를 입력해 주세요."));
    }
}
