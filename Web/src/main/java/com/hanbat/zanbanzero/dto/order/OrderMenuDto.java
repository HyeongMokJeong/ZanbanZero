package com.hanbat.zanbanzero.dto.order;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hanbat.zanbanzero.Entity.order.OrderMenu;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderMenuDto {

    private Long id;
    private int cost;
    private Map<String, Integer> menu;

    private static OrderMenuDto createOrderMenuDto(OrderMenu orderMenu) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return new OrderMenuDto(
                orderMenu.getId(),
                orderMenu.getCost(),
                objectMapper.readValue(orderMenu.getMenu(), new TypeReference<Map<String, Integer>>() {})
        );
    }
}
