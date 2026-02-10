package com.aceleradev.backend.services;

import com.aceleradev.backend.commons.dto.OrderDTO;
import com.aceleradev.backend.commons.dto.OrderItemDto;
import com.aceleradev.backend.commons.enums.OrderStatus;
import com.aceleradev.backend.entities.*;
import com.aceleradev.backend.repositories.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Order createOrder(OrderDTO orderDTO) {
        Order orderEntity = new Order();
        OrderItem orderItemEntity = new OrderItem();

        Employee employee = employeeRepository.findById(orderDTO.getEmployee())
                .orElseThrow(() -> new EntityNotFoundException("Not Found"));

        Customer customer = customerRepository.findById(orderDTO.getCustomer())
                .orElseThrow(() -> new EntityNotFoundException("Not Found"));

        orderEntity.setOrderStatus(OrderStatus.WAITING_PAYMENT);
        orderEntity.setEmployeeId(employee);
        orderEntity.setCustomerId(customer);

        orderRepository.save(orderEntity);

        for (OrderItemDto dto : orderDTO.getItems()) {
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

    public OrderDTO getOrderById(Long id) {
        Order findOrder = orderRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Not Found"));
        OrderDTO orderDTO = new OrderDTO();
        OrderItemDto orderItemDto = new OrderItemDto();
        List<OrderItem> orderItems = orderItemRepository.findAllByOrder(findOrder);

        orderDTO.setOrderStatus(findOrder.getOrderStatus().name());
        orderDTO.setCustomerId(findOrder.getCustomerId().getId());
        orderDTO.setEmployeeId(findOrder.getEmployeeId().getId());
        List<OrderItemDto> itemsList = orderItems.stream().map(item ->
                {
                    orderItemDto.setName(item.getProduct().getName());
                    orderItemDto.setQuantity(item.getQuantity());
                    orderItemDto.setProductId(item.getProduct().getId());

                    return orderItemDto;
                }
        ).toList();
        orderDTO.setItems(itemsList);

        return orderDTO;
    }

}
