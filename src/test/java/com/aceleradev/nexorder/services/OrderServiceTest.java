package com.aceleradev.nexorder.services;

import com.aceleradev.nexorder.commons.enums.OrderStatus;
import com.aceleradev.nexorder.dtos.order.OrderDTO;
import com.aceleradev.nexorder.dtos.orderitem.OrderItemDto;
import com.aceleradev.nexorder.entities.Customer;
import com.aceleradev.nexorder.entities.Employee;
import com.aceleradev.nexorder.entities.Order;
import com.aceleradev.nexorder.entities.OrderItem;
import com.aceleradev.nexorder.entities.Product;
import com.aceleradev.nexorder.repositories.CustomerRepository;
import com.aceleradev.nexorder.repositories.EmployeeRepository;
import com.aceleradev.nexorder.repositories.OrderItemRepository;
import com.aceleradev.nexorder.repositories.OrderRepository;
import com.aceleradev.nexorder.repositories.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private OrderItemRepository orderItemRepository;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private OrderService orderService;

    @Test
    void createOrderShouldCreateOrderAndItemsWhenDataIsValid() {
        OrderDTO dto = new OrderDTO();
        dto.setEmployeeId(20L);
        dto.setCustomerId(10L);
        dto.setItems(List.of(itemDto(101L, 2), itemDto(102L, 1)));

        Employee employee = new Employee();
        employee.setId(20L);
        Customer customer = new Customer();
        customer.setId(10L);

        Product product1 = new Product();
        product1.setId(101L);
        product1.setPrice(12.5);
        product1.setName("Burger");

        Product product2 = new Product();
        product2.setId(102L);
        product2.setPrice(7.0);
        product2.setName("Soda");

        when(employeeRepository.findById(20L)).thenReturn(Optional.of(employee));
        when(customerRepository.findById(10L)).thenReturn(Optional.of(customer));
        when(productRepository.findById(101L)).thenReturn(Optional.of(product1));
        when(productRepository.findById(102L)).thenReturn(Optional.of(product2));
        when(orderRepository.save(any(Order.class))).thenAnswer(invocation -> invocation.getArgument(0));
        when(orderItemRepository.save(any(OrderItem.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Order created = orderService.createOrder(dto);

        assertNotNull(created);
        assertEquals(OrderStatus.WAITING_PAYMENT, created.getOrderStatus());
        assertEquals(20L, created.getEmployeeId().getId());
        assertEquals(10L, created.getCustomerId().getId());

        ArgumentCaptor<OrderItem> itemCaptor = ArgumentCaptor.forClass(OrderItem.class);
        verify(orderItemRepository, times(2)).save(itemCaptor.capture());
        List<OrderItem> savedItems = itemCaptor.getAllValues();

        assertEquals(2, savedItems.get(0).getQuantity());
        assertEquals(12.5, savedItems.get(0).getPrice());
        assertEquals(101L, savedItems.get(0).getProduct().getId());

        assertEquals(1, savedItems.get(1).getQuantity());
        assertEquals(7.0, savedItems.get(1).getPrice());
        assertEquals(102L, savedItems.get(1).getProduct().getId());
    }

    @Test
    void createOrderShouldThrowWhenEmployeeNotFound() {
        OrderDTO dto = new OrderDTO();
        dto.setEmployeeId(20L);
        dto.setCustomerId(10L);

        when(employeeRepository.findById(20L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> orderService.createOrder(dto));
    }

    @Test
    void createOrderShouldThrowWhenCustomerNotFound() {
        OrderDTO dto = new OrderDTO();
        dto.setEmployeeId(20L);
        dto.setCustomerId(10L);

        Employee employee = new Employee();
        employee.setId(20L);

        when(employeeRepository.findById(20L)).thenReturn(Optional.of(employee));
        when(customerRepository.findById(10L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> orderService.createOrder(dto));
    }

    @Test
    void createOrderShouldThrowWhenProductNotFound() {
        OrderDTO dto = new OrderDTO();
        dto.setEmployeeId(20L);
        dto.setCustomerId(10L);
        dto.setItems(List.of(itemDto(101L, 2)));

        Employee employee = new Employee();
        employee.setId(20L);
        Customer customer = new Customer();
        customer.setId(10L);

        when(employeeRepository.findById(20L)).thenReturn(Optional.of(employee));
        when(customerRepository.findById(10L)).thenReturn(Optional.of(customer));
        when(productRepository.findById(101L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> orderService.createOrder(dto));
    }

    @Test
    void getOrderByIdShouldReturnOrderDTOWithTotal() {
        Order order = new Order();
        order.setId(1L);
        order.setOrderStatus(OrderStatus.WAITING_PAYMENT);

        Customer customer = new Customer();
        customer.setId(10L);
        order.setCustomerId(customer);

        Employee employee = new Employee();
        employee.setId(20L);
        order.setEmployeeId(employee);

        Product product1 = new Product();
        product1.setId(101L);
        product1.setName("Burger");
        product1.setPrice(12.5);

        Product product2 = new Product();
        product2.setId(102L);
        product2.setName("Soda");
        product2.setPrice(7.0);

        OrderItem item1 = new OrderItem();
        item1.setOrderId(order);
        item1.setProduct(product1);
        item1.setQuantity(2);
        item1.setPrice(12.5);

        OrderItem item2 = new OrderItem();
        item2.setOrderId(order);
        item2.setProduct(product2);
        item2.setQuantity(1);
        item2.setPrice(7.0);

        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));
        when(orderItemRepository.findAllByOrder(order)).thenReturn(List.of(item1, item2));

        OrderDTO result = orderService.getOrderById(1L);

        assertEquals("WAITING_PAYMENT", result.getOrderStatus());
        assertEquals(10L, result.getCustomer());
        assertEquals(20L, result.getEmployee());
        assertEquals(32.0, result.getTotal());
        assertEquals(2, result.getItems().size());
        assertEquals(101L, result.getItems().get(0).getProductId());
        assertEquals(2, result.getItems().get(0).getQuantity());
        assertEquals(102L, result.getItems().get(1).getProductId());
        assertEquals(1, result.getItems().get(1).getQuantity());
    }

    @Test
    void getOrderByIdShouldThrowWhenOrderNotFound() {
        when(orderRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> orderService.getOrderById(1L));
    }

    private OrderItemDto itemDto(Long productId, int quantity) {
        OrderItemDto item = new OrderItemDto();
        item.setProductId(productId);
        item.setQuantity(quantity);
        return item;
    }
}
