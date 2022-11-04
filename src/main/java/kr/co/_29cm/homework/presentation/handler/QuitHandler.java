package kr.co._29cm.homework.presentation.handler;

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
