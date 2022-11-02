package com.musinsa.presentation.handler;

public interface Handler {

    boolean isSupport(String command);

    boolean execute();
}
