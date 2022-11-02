package com.musinsa.presentation.view;

import java.util.Scanner;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    public static String printCommandInformation() {
        System.out.print("입력(o[order]: 주문, q[quit]: 종료) : ");
        return scanner.nextLine();
    }

    public static String printSerialNumberInputMessage() {
        System.out.print("상품번호 : ");
        return scanner.nextLine();
    }

    public static String printQuantityInputMessage() {
        System.out.print("수량 : ");
        return scanner.nextLine();
    }
}
