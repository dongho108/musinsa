package com.musinsa.presentation;

import org.springframework.stereotype.Controller;

@Controller
public class MainController {

    private final ProductInitializer productInitializer;

    public MainController(final ProductInitializer productInitializer) {
        this.productInitializer = productInitializer;
    }

    public void run() {
        productInitializer.initData();
    }
}
