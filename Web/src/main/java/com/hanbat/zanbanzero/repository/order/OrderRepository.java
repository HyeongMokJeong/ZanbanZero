package com.hanbat.zanbanzero.repository.order;

import com.hanbat.zanbanzero.Entity.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findByUserId(long l);

    @Query("select o from Order o join fetch o.orderMenu where o.user.id = :id")
    List<Order> getOrdres(@Param("id") Long id);
}
