package com.hanbat.zanbanzero.repository.order;

import com.hanbat.zanbanzero.Entity.order.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends MongoRepository<Order, String> {

    public Optional<Order> findById(String id);

    public List<Order> findByUserId(Long userId);
}
