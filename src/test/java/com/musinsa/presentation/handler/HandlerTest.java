package com.musinsa.presentation.handler;

import com.musinsa.application.CartService;
import com.musinsa.application.OrderService;
import com.musinsa.application.ProductService;
import com.musinsa.support.test.ServiceTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@ServiceTest
public class HandlerTest {

    @MockBean
    protected ProductService productService;

    @MockBean
    protected CartService cartService;

    @MockBean
    protected OrderService orderService;
}
