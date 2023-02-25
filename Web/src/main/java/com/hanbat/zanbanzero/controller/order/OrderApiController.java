package com.hanbat.zanbanzero.controller.order;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hanbat.zanbanzero.dto.order.OrderDto;
import com.hanbat.zanbanzero.dto.order.OrderMenuDto;
import com.hanbat.zanbanzero.dto.user.UserDto;
import com.hanbat.zanbanzero.exception.controller.exceptions.CantFindByIdException;
import com.hanbat.zanbanzero.exception.controller.exceptions.RequestDataisNull;
import com.hanbat.zanbanzero.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderApiController {

    private final OrderService orderService;

    @PostMapping("/api/user/{id}/order/add")
    public ResponseEntity<String> addOrder(@RequestBody OrderMenuDto orderDto, @PathVariable Long id) throws RequestDataisNull, JsonProcessingException {
        orderService.addOrder(orderDto, id);
        return ResponseEntity.status(HttpStatus.OK).body("등록되었습니다.");
    }

    @PatchMapping("/api/user/order/{id}/del")
    public ResponseEntity<String> deleteOrder(@PathVariable Long id) throws CantFindByIdException {
        orderService.deleteOrder(id);
        return ResponseEntity.status(HttpStatus.OK).body("취소되었습니다.");
    }

    @GetMapping("/api/user/{id}/order/show")
    public ResponseEntity<List<OrderDto>> getOrders(@PathVariable Long id) {
        List<OrderDto> result = orderService.getOrders(id);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/api/user/order/details")
    public ResponseEntity<OrderDto> getOrderDetails(@RequestBody OrderDto orderDto) throws CantFindByIdException {
        OrderDto result = orderService.getOrderDetails(orderDto.getId());
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
