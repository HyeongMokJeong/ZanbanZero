package com.hanbat.zanbanzero.Entity.order;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hanbat.zanbanzero.dto.order.OrderMenuDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class OrderMenu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int cost;
    private String menu;

    public static OrderMenu createOrderMenu(OrderMenuDto dto) throws JsonProcessingException {
        return new OrderMenu(
                dto.getId(),
                dto.getCost(),
                new ObjectMapper().writeValueAsString(dto.getMenu())
        );
    }
}
