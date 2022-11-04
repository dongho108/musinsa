package kr.co._29cm.homework.presentation.handler;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import kr.co._29cm.homework.support.test.ServiceTest;
import java.util.NoSuchElementException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;

@ServiceTest
class ConsoleHandlerMapperTest{

    @Autowired
    private HandlerMapper handlerMapper;

    @ParameterizedTest
    @ValueSource(strings = {"o", "order"})
    void 주문_명령어는_OrderHandler_를_반환한다(String command) {
        final Handler handler = handlerMapper.getHandler(command);
        assertThat(handler).isInstanceOf(OrderHandler.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"q", "quit"})
    void 종료_명령어는_QuitHandler_를_반환한다(String command) {
        final Handler handler = handlerMapper.getHandler(command);
        assertThat(handler).isInstanceOf(QuitHandler.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"a", "b", "c", "!", "", " ", "quIt", "Order"})
    void 지원하는_명령어가_없으면_예외가_발생한다(String command) {
        assertThatThrownBy(() -> handlerMapper.getHandler(command))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage("해당하는 명령이 없습니다. q 혹은 o 명령어를 입력해 주세요.");
    }
}
