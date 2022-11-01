package com.musinsa.presentation;

import java.util.Scanner;
import org.springframework.stereotype.Controller;

@Controller
public class MainController {

    private final ProductInitializer productInitializer;
    private final Scanner scanner = new Scanner(System.in);

    public MainController(final ProductInitializer productInitializer) {
        this.productInitializer = productInitializer;
    }

    public void init() {
        productInitializer.initData();
    }

    public void run() {
        String input;

        while (!(input = scanner.nextLine()).equals("q")) {
            System.out.println(input);
        }
    }
}
