package kr.co._29cm.homework.presentation.handler;

import kr.co._29cm.homework.application.CartService;
import kr.co._29cm.homework.application.OrderService;
import kr.co._29cm.homework.application.ProductService;
import kr.co._29cm.homework.support.test.ServiceTest;
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
