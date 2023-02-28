package com.hanbat.zanbanzero.entity.order;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hanbat.zanbanzero.dto.order.OrderDetailsDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class OrderDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Order orders;

    private int cost;
    private String menu;
    private int payment;

    public static OrderDetails createOrderMenu(OrderDetailsDto dto, Order order) throws JsonProcessingException {
        return new OrderDetails(
                dto.getId(),
                order,
                dto.getCost(),
                new ObjectMapper().writeValueAsString(dto.getMenu()),
                dto.getPayment()
        );
    }
}
