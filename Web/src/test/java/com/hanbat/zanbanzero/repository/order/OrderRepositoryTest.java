package com.hanbat.zanbanzero.repository.order;

import com.hanbat.zanbanzero.Entity.order.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void testSearch() {
        Order order = (Order) orderRepository.findByUserId(1L);
        System.out.println(order.toString());
    }
}