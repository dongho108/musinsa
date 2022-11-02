package com.musinsa.presentation.handler;

public class QuitHandler implements Handler{

    @Override
    public boolean isSupport(final String command) {
        return command.equals("q") || command.equals("quit");
    }

    @Override
    public boolean execute() {
        return false;
    }
}
