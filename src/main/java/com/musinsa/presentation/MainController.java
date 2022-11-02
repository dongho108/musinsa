package com.musinsa.presentation;

import com.musinsa.application.ProductService;
import com.musinsa.presentation.view.OutputView;
import java.util.Scanner;
import org.springframework.stereotype.Controller;

@Controller
public class MainController {

    private static final Scanner scanner = new Scanner(System.in);

    private final ProductInitializer productInitializer;
    private final ProductService productService;

    public MainController(final ProductInitializer productInitializer, final ProductService productService) {
        this.productInitializer = productInitializer;
        this.productService = productService;
    }

    public void init() {
        productInitializer.initData();
    }

    public void run() {
        while (true) {
            OutputView.printCommandInformation();
            final String input = scanner.nextLine();
            if (input.equals("q") || input.equals("quit")) {
                break;
            }
            if (input.equals("o")) {
                OutputView.printProducts(productService.findAll());
            }
        }
    }
}
