package com.aceleradev.nexorder.integration;

import com.aceleradev.nexorder.commons.enums.OrderStatus;
import com.aceleradev.nexorder.commons.enums.PaymentMethod;
import com.aceleradev.nexorder.dtos.payment.PaymentDto;
import com.aceleradev.nexorder.entities.Customer;
import com.aceleradev.nexorder.entities.Order;
import com.aceleradev.nexorder.entities.OrderItem;
import com.aceleradev.nexorder.entities.Product;
import com.aceleradev.nexorder.repositories.CustomerRepository;
import com.aceleradev.nexorder.repositories.OrderItemRepository;
import com.aceleradev.nexorder.repositories.OrderRepository;
import com.aceleradev.nexorder.repositories.PaymentRepository;
import com.aceleradev.nexorder.repositories.ProductRepository;
import com.aceleradev.nexorder.services.PaymentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class OrderPaymentIntegrationTest {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Test
    void shouldUseTestConfigSeedAndProcessPaymentForWaitingOrder() {
        Customer customer = customerRepository.findAll().stream()
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Expected seeded customer from TestConfig"));

        Product product = productRepository.findAll().stream()
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Expected seeded product from TestConfig"));

        Order waitingOrder = new Order();
        waitingOrder.setCustomerId(customer);
        waitingOrder.setOrderStatus(OrderStatus.WAITING_PAYMENT);
        waitingOrder = orderRepository.save(waitingOrder);
        final long waitingOrderId = waitingOrder.getId();

        OrderItem orderItem = new OrderItem();
        orderItem.setOrderId(waitingOrder);
        orderItem.setProduct(product);
        orderItem.setPrice(product.getPrice());
        orderItem.setQuantity(2);
        orderItemRepository.save(orderItem);

        PaymentDto paymentDto = new PaymentDto();
        paymentDto.setPaymentMethod(PaymentMethod.PIX);
        paymentDto.setTotalValue(product.getPrice() * 2);

        paymentService.makePayment(customer.getId(), paymentDto);

        Order updatedOrder = orderRepository.findById(waitingOrderId).orElseThrow();
        assertEquals(OrderStatus.PAID, updatedOrder.getOrderStatus());

        assertTrue(paymentRepository.findAll().stream()
                .anyMatch(p -> p.getOrder() != null && p.getOrder().getId() == waitingOrderId));
    }

    @Test
    void testConfigShouldSeedInitialPaidOrder() {
        List<Order> orders = orderRepository.findAll();
        assertNotNull(orders);
        assertTrue(orders.stream().anyMatch(order -> order.getOrderStatus() == OrderStatus.PAID));
    }
}
