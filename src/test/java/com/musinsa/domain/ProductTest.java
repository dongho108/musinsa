package com.musinsa.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.math.BigDecimal;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ProductTest {

    @ParameterizedTest
    @ValueSource(ints = {-1, -100})
    void 상품의_판매가격이_음수이면_상품을_생성하면_예외가_발생한다(int price) {
        assertThatThrownBy(() -> getProductRequest("111", "29CM", BigDecimal.valueOf(price), 10))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, -100})
    void 상품의_재고가_음수이면_상품을_생성하면_예외가_발생한다(int stock) {
        assertThatThrownBy(() -> getProductRequest("111", "29CM", BigDecimal.valueOf(1000), stock))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(ints = {11, 12, 100})
    void 수량을_상품의_재고보다_더많이_감소시키면_예외가_발생한다(int quantity) {
        final Product product = getProductRequest("111", "29CM", BigDecimal.valueOf(1000), 10);
        assertThatThrownBy(() -> product.reduceStock(quantity))
                .isInstanceOf(SoldOutException.class);
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, -2, -100})
    void 수량이_음수이면_예외가_발생한다(int quantity) {
        final Product product = getProductRequest("111", "29CM", BigDecimal.valueOf(1000), 10);
        assertThatThrownBy(() -> product.reduceStock(quantity))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 상품의_재고를_감소시킨다() {
        final Product product = getProductRequest("111", "29CM", BigDecimal.valueOf(1000), 10);
        product.reduceStock(6);
        assertThat(product.getStock()).isEqualTo(4);
    }

    @Test
    void 동시에_재고를_감소시켜도_수량만큼_재고가_감소해야한다() throws InterruptedException {
        // given
        final Product product = getProductRequest("111", "29CM", BigDecimal.valueOf(1000), 2000);
        final int numberOfThreads = 1000;
        final CountDownLatch start = new CountDownLatch(numberOfThreads);
        final CountDownLatch end = new CountDownLatch(numberOfThreads);
        final ExecutorService service = Executors.newFixedThreadPool(numberOfThreads);

        // when
        for (int i = 0; i < numberOfThreads; i++) {
            service.execute(() -> {
                try {
                    start.countDown();
                    start.await();
                    product.reduceStock(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    end.countDown();
                }
            });
        }
        end.await();

        // then
        assertThat(product.getStock()).isEqualTo(1000);
    }

    @Test
    void 동시에_재고를_감소시켜도_재고가_부족하면_예외가_발생해야한다() throws InterruptedException {
        // given
        final Product product = getProductRequest("111", "29CM", BigDecimal.valueOf(1000), 2000);
        final int numberOfThreads = 2100;

        final AtomicInteger fail = new AtomicInteger(0);
        final CountDownLatch start = new CountDownLatch(numberOfThreads);
        final CountDownLatch end = new CountDownLatch(numberOfThreads);
        final ExecutorService service = Executors.newFixedThreadPool(numberOfThreads);

        // when
        for (int i = 0; i < numberOfThreads; i++) {
            service.execute(() -> {
                try {
                    start.countDown();
                    start.await();
                    product.reduceStock(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (SoldOutException e) {
                    fail.incrementAndGet();
                } finally {
                    end.countDown();
                }
            });
        }
        end.await();

        // then
        assertThat(fail.get()).isEqualTo(100);
    }

    public static Product getProductRequest(final String serialNumber,
                                            final String name,
                                            final BigDecimal price,
                                            final Integer stock) {
        return new Product(serialNumber, name, price, stock);
    }
}
