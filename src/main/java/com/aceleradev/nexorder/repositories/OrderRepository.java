package com.aceleradev.nexorder.repositories;

import com.aceleradev.nexorder.entities.Customer;
import com.aceleradev.nexorder.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    public List<Order> findAllByCustomer(Customer customer);
}
