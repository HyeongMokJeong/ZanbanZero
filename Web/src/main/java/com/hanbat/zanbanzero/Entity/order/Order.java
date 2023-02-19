package com.hanbat.zanbanzero.Entity.order;

import com.hanbat.zanbanzero.dto.order.OrderDto;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Document(collection = "order")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Order {
    @Id
    private String id;
    private Long storeId;
    private Long userId;
    private Map<Long, Integer> menu;
    private String date;
    private Boolean payed;

    public static Order createOrder(OrderDto orderDto) {
        return new Order(
                orderDto.getId(),
                orderDto.getStoreId(),
                orderDto.getUserId(),
                orderDto.getMenu(),
                orderDto.getDate(),
                orderDto.getPayed()
        );
    }
}
