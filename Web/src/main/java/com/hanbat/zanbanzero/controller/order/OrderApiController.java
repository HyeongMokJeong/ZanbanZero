package com.hanbat.zanbanzero.controller.order;

import com.hanbat.zanbanzero.dto.order.OrderDto;
import com.hanbat.zanbanzero.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderApiController {

    private final OrderService orderService;

    @PostMapping("/api/user/order/add")
    public ResponseEntity<String> addOrder(@RequestBody OrderDto orderDto) {
        orderService.addOrder(orderDto);
        return ResponseEntity.status(HttpStatus.OK).body("등록되었습니다.");
    }
}
