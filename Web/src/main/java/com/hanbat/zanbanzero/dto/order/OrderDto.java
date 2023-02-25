package com.hanbat.zanbanzero.dto.order;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hanbat.zanbanzero.Entity.order.Order;
import lombok.*;

import java.util.Map;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder(builderMethodName = "OrderDtoBuilder")
public class OrderDto {

    private Long id;
    private Long userId;
    private Map<String, Integer> orderMenu;
    private String updated;
    private int payed;

    public static OrderDto createOrderDto(Order order) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return new OrderDto(
                order.getId(),
                order.getUser().getId(),
                objectMapper.readValue(order.getOrderMenu().getMenu(), new TypeReference<Map<String, Integer>>() {}),
                order.getUpdated(),
                order.getPayed()
        );
    }

    public static OrderDtoBuilder builder() {
        return OrderDtoBuilder();
    }
}
