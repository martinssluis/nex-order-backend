package com.aceleradev.backend.repositories;

import com.aceleradev.backend.entities.Customer;
import com.aceleradev.backend.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    public List<Order> findAllByCustomer(Customer customer);
}
