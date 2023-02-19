package com.hanbat.zanbanzero.controller.order;

import com.hanbat.zanbanzero.dto.order.OrderDto;
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

    @PostMapping("/api/manager/order/add")
    public ResponseEntity<String> addOrder(@RequestBody OrderDto orderDto) throws RequestDataisNull {
        orderService.addOrder(orderDto);
        return ResponseEntity.status(HttpStatus.OK).body("등록되었습니다.");
    }

    @PatchMapping("/api/manager/order/del")
    public ResponseEntity<String> deleteOrder(@RequestBody OrderDto orderDto) throws CantFindByIdException {
        orderService.deleteOrder(orderDto.getId());
        return ResponseEntity.status(HttpStatus.OK).body("취소되었습니다.");
    }

    @GetMapping("/api/user/orders")
    public ResponseEntity<List<OrderDto>> getOrders(@RequestBody OrderDto orderDto) throws CantFindByIdException {
        List<OrderDto> result = orderService.getOrders(orderDto.getUserId());
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
