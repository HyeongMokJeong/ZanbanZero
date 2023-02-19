package com.hanbat.zanbanzero.dto.order;

import com.hanbat.zanbanzero.Entity.order.Order;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDto {

    private String id;
    private Long storeId;
    private Long userId;
    private Map<Long, Integer> menu;
    private String date;
    private Boolean payed;

    public OrderDto(Long store_id, Long user_id, Map<Long, Integer> menu) {
        this.storeId = store_id;
        this.userId = user_id;
        this.menu = menu;
    }

    public OrderDto(Long store_id, Long user_id, Map<Long, Integer> menu, String date, Boolean payed) {
        this.storeId = store_id;
        this.userId = user_id;
        this.menu = menu;
        this.date = date;
        this.payed = payed;
    }

    public static OrderDto createOrderDto(Order order) {
        return new OrderDto(
                order.getId(),
                order.getStoreId(),
                order.getUserId(),
                order.getMenu(),
                order.getDate(),
                order.getPayed()
        );
    }
}
