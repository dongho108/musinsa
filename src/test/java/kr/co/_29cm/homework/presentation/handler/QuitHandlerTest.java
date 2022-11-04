package kr.co._29cm.homework.presentation.handler;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class QuitHandlerTest extends HandlerTest{

    @ParameterizedTest
    @ValueSource(strings = {"q", "quit"})
    void 지원하는_명령어는_true_를_반환한다(String command) {
        final Handler handler = new QuitHandler();
        assertThat(handler.isSupport(command)).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"o", "order", "aaa", "111", "stop"})
    void 지원하지않는_명령어는_false_를_반환한다(String command) {
        final Handler handler = new QuitHandler();
        assertThat(handler.isSupport(command)).isFalse();
    }

    @Test
    void 수행뒤_false_를_반환한다() {
        final Handler handler = new QuitHandler();
        assertThat(handler.execute()).isFalse();
    }
}
