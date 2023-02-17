package com.hanbat.zanbanzero.repository.order;

import com.hanbat.zanbanzero.dto.order.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

    private final MongoTemplate mongoTemplate;

    public void add(OrderDto dto) {
        mongoTemplate.insert(dto);
    }
}
