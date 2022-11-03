package com.musinsa;

import com.musinsa.presentation.MainController;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TwentyninecmApplication implements CommandLineRunner {

	private final MainController mainController;

	public TwentyninecmApplication(final MainController mainController) {
		this.mainController = mainController;
	}

	public static void main(String[] args) {
		SpringApplication.run(TwentyninecmApplication.class, args);
	}

	@Override
	public void run(final String... args) throws Exception {
		mainController.init();
		mainController.run();
	}
}
