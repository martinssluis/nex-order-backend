package com.aceleradev.nexorder.repositories;

import com.aceleradev.nexorder.entities.Order;
import com.aceleradev.nexorder.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    List<OrderItem> findAllByOrder(Order order);
}
