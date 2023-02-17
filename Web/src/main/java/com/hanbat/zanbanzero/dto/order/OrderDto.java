package com.hanbat.zanbanzero.dto.order;

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
@Document("order")
public class OrderDto {

    private Long store_id;
    private Long user_id;
    private Map<Long, Integer> menu;
    private String date;
    private Boolean payed;

    public OrderDto(Long store_id, Long user_id, Map<Long, Integer> menu) {
        this.store_id = store_id;
        this.user_id = user_id;
        this.menu = menu;
    }
}
