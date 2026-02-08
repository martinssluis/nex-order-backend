package com.aceleradev.backend.services;

import com.aceleradev.backend.commons.dto.CreateOrderDTO;
import com.aceleradev.backend.commons.dto.OrderItemDto;
import com.aceleradev.backend.commons.enums.OrderStatus;
import com.aceleradev.backend.entities.*;
import com.aceleradev.backend.repositories.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    public EmployeeRepository employeeRepository;

    @Autowired
    public ProductRepository productRepository;

    public Order createOrder(CreateOrderDTO createOrderDTO) {
        Order orderEntity = new Order();
        OrderItem orderItemEntity = new OrderItem();

        Employee employee = employeeRepository.findById(createOrderDTO.getEmployee())
                .orElseThrow(() -> new EntityNotFoundException("Not Found"));

        Customer customer = customerRepository.findById(createOrderDTO.getCustomer())
                .orElseThrow(() -> new EntityNotFoundException("Not Found"));

        orderEntity.setOrderStatus(OrderStatus.valueOf(createOrderDTO.getOrderStatus()));
        orderEntity.setEmployeeId(employee);
        orderEntity.setCustomerId(customer);

        orderRepository.save(orderEntity);

        for (OrderItemDto dto : createOrderDTO.getItems()) {
            Product product = productRepository.findById(dto.getProductId())
                    .orElseThrow(() -> new EntityNotFoundException("Not Found"));

            orderItemEntity.setOrderId(orderEntity);
            orderItemEntity.setProduct(product);
            orderItemEntity.setQuantity(dto.getQuantity());
            orderItemEntity.setPrice(product.getPrice());
            orderItemRepository.save(orderItemEntity);
        }

        return orderEntity;
    }
}
