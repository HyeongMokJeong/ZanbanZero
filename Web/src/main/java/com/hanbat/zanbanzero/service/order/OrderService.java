package com.hanbat.zanbanzero.service.order;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hanbat.zanbanzero.Entity.order.Order;
import com.hanbat.zanbanzero.Entity.order.OrderMenu;
import com.hanbat.zanbanzero.aop.annotation.RunningTime;
import com.hanbat.zanbanzero.dto.order.OrderDto;
import com.hanbat.zanbanzero.dto.order.OrderMenuDto;
import com.hanbat.zanbanzero.exception.controller.exceptions.CantFindByIdException;
import com.hanbat.zanbanzero.exception.controller.exceptions.RequestDataisNull;
import com.hanbat.zanbanzero.repository.order.OrderMenuRepository;
import com.hanbat.zanbanzero.repository.order.OrderRepository;
import com.hanbat.zanbanzero.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StopWatch;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final OrderMenuRepository orderMenuRepository;

    @Transactional
    public void addOrder(OrderMenuDto dto, Long id) throws RequestDataisNull, JsonProcessingException {
        if (dto.getMenu() == null) {
            List<String> nullList = new ArrayList<>();
            nullList.add("Menu");
            throw new RequestDataisNull("데이터가 부족합니다. - " + nullList);
        }

        OrderMenu orderMenu = OrderMenu.createOrderMenu(dto);
        OrderMenu result = orderMenuRepository.save(orderMenu);

        OrderDto orderDto = OrderDto.builder()
                .updated(new Date().toString())
                .payed(1)
                .userId(id)
                .build();

        Order order = Order.createOrder(orderDto, userRepository.getReferenceById(id), orderMenuRepository.getReferenceById(result.getId()));
        orderRepository.save(order);
    }

    @Transactional
    public void deleteOrder(Long id) throws CantFindByIdException {
        Order order = orderRepository.findById(id).orElse(null);
        if (order == null) {
            throw new CantFindByIdException("잘못된 id 입니다.");
        }
        order.setPayed(0);
        orderRepository.save(order);
    }

    public List<OrderDto> getOrders(Long id) {
        List<Order> orders = orderRepository.getOrdres(id);

        return orders.stream()
                .map(order -> {
                    try {
                        return OrderDto.createOrderDto(order);
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.toList());
    }

    public OrderDto getOrderDetails(Long orderId) throws CantFindByIdException {
        return null;
    }
}
