package com.musinsa.domain;

public class SoldOutException extends RuntimeException {
    public SoldOutException() {
        super("상품의 재고가 부족합니다.");
    }
}
