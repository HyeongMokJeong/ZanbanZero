package com.hanbat.zanbanzero.repository.order;

import com.hanbat.zanbanzero.entity.order.Order;
import com.hanbat.zanbanzero.entity.order.OrderDetails;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Order> {

    @Query("select o from OrderDetails o join fetch o.orders where o.id = :id")
    //@EntityGraph(attributePaths = {})
    OrderDetails getOrderDetails(@Param("id") Long id);
}
