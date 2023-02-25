package com.hanbat.zanbanzero.service.order;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StopWatch;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class OrderServiceTest {

    @Test
    void addOrder() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        stopWatch.stop();
        log.info("총 수행 시간 => {} sec", stopWatch.getTotalTimeSeconds());
    }

    @Test
    void deleteOrder() {
    }

    @Test
    void getOrders() {
    }

    @Test
    void getOrderDetails() {
    }
}