package com.hanbat.zanbanzero.Entity.order;

import com.hanbat.zanbanzero.Entity.user.User;
import com.hanbat.zanbanzero.dto.order.OrderDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_menu_id")
    @ToString.Exclude
    private OrderMenu orderMenu;

    private String updated;
    private int payed;

    public static Order createOrder(OrderDto dto, User user, OrderMenu orderMenu) {
        return new Order(
                dto.getId(),
                user,
                orderMenu,
                dto.getUpdated(),
                dto.getPayed()
        );
    }

    public void setPayed(int payed) {
        this.payed = payed;
    }
}
