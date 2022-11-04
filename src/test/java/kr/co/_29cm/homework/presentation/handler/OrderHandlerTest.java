package kr.co._29cm.homework.presentation.handler;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mockStatic;

import kr.co._29cm.homework.presentation.view.InputView;
import kr.co._29cm.homework.presentation.view.OutputView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class OrderHandlerTest extends HandlerTest{

    private Handler orderHandler;

    @BeforeEach
    void setUp() {
        orderHandler = new OrderHandler(productService, cartService, orderService);
    }

    @ParameterizedTest
    @ValueSource(strings = {"o", "order"})
    void 지원하는_명령어는_true_를_반환한다(String command) {
        assertThat(orderHandler.isSupport(command)).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"q", "quit", "aaa", "111", "stop"})
    void 지원하지않는_명령어는_false_를_반환한다(String command) {
        assertThat(orderHandler.isSupport(command)).isFalse();
    }

    @Test
    void 수행뒤_true_를_반환한다() {
        mockStatic(InputView.class);
        mockStatic(OutputView.class);
        given(InputView.printSerialNumberInputMessage()).willReturn(" ");
        given(InputView.printQuantityInputMessage()).willReturn(" ");
        given(orderService.create(any())).willReturn(null);

        assertThat(orderHandler.execute()).isTrue();
    }
}
