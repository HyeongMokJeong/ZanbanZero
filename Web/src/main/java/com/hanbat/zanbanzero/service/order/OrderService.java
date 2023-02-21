package com.hanbat.zanbanzero.service.order;

import com.hanbat.zanbanzero.Entity.order.Order;
import com.hanbat.zanbanzero.dto.order.OrderDto;
import com.hanbat.zanbanzero.exception.controller.exceptions.CantFindByIdException;
import com.hanbat.zanbanzero.exception.controller.exceptions.RequestDataisNull;
import com.hanbat.zanbanzero.repository.order.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public void addOrder(OrderDto orderDto) throws RequestDataisNull {
        if (orderDto.getStoreId() == null || orderDto.getUserId() == null || orderDto.getMenu() == null) {
            List<String> nullList = new ArrayList<>();
            if (orderDto.getStoreId() == null) nullList.add("StoreId");
            if (orderDto.getUserId() == null) nullList.add("UserId");
            if (orderDto.getMenu() == null) nullList.add("Menu");
            throw new RequestDataisNull("데이터가 부족합니다. - " + nullList);
        }

        orderDto.setDate(new Date().toString());
        orderDto.setPayed(true);

        orderRepository.insert(Order.createOrder(orderDto));
    }

    public void deleteOrder(String orderId) throws CantFindByIdException {
        Order order = orderRepository.findById(orderId).orElse(null);
        if (order == null) {
            throw new CantFindByIdException("잘못된 id 입니다.");
        }
        order.setPayed(false);
        orderRepository.save(order);
    }

    public List<OrderDto> getOrders(Long userId) {
        List<Order> orders = orderRepository.findByUserId(userId);
        return orders.stream()
                .map(order -> OrderDto.createOrderDto(order))
                .collect(Collectors.toList());
    }

    public OrderDto getOrderDetails(String orderId) throws CantFindByIdException {
        Order order = orderRepository.findById(orderId).orElse(null);
        if (order == null) {
            throw new CantFindByIdException("잘못된 id 입니다.");
        }
        return OrderDto.createOrderDto(order);
    }
}
