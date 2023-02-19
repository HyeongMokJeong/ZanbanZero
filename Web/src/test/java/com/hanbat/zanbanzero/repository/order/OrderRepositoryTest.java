package com.hanbat.zanbanzero.repository.order;

import com.hanbat.zanbanzero.Entity.order.Order;
import com.hanbat.zanbanzero.dto.order.OrderDto;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.Document;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    @Test
    @Transactional
    public void testInsert() {
        Map<Long, Integer> testMap = new HashMap<>();
        testMap.put(1L, 3);
        testMap.put(2L, 1);
        OrderDto orderDto = new OrderDto(1L, 1L, testMap, new Date().toString(), true);
        orderRepository.insert(Order.createOrder(orderDto));
    }

    @Test
    public void testSearch() {
        Order order = (Order) orderRepository.findByUserId(1L);
        System.out.println(order.toString());
    }
}