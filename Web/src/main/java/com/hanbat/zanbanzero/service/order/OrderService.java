package com.hanbat.zanbanzero.service.order;

import com.hanbat.zanbanzero.dto.order.OrderDto;
import com.hanbat.zanbanzero.repository.order.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public void addOrder(OrderDto orderDto) {
        orderDto.setDate(new Date().toString());
        orderDto.setPayed(true);
        orderRepository.add(orderDto);
    }
}
