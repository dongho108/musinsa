package kr.co._29cm.homework.presentation.handler;

public interface Handler {

    boolean isSupport(String command);

    boolean execute();
}
