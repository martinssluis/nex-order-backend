package com.aceleradev.nexorder.services;

import com.aceleradev.nexorder.commons.enums.OrderStatus;
import com.aceleradev.nexorder.commons.enums.PaymentMethod;
import com.aceleradev.nexorder.dtos.payment.PaymentDto;
import com.aceleradev.nexorder.entities.Customer;
import com.aceleradev.nexorder.entities.Order;
import com.aceleradev.nexorder.entities.OrderItem;
import com.aceleradev.nexorder.entities.Payment;
import com.aceleradev.nexorder.repositories.CustomerRepository;
import com.aceleradev.nexorder.repositories.OrderItemRepository;
import com.aceleradev.nexorder.repositories.OrderRepository;
import com.aceleradev.nexorder.repositories.PaymentRepository;
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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PaymentServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private OrderItemRepository orderItemRepository;

    @Mock
    private PaymentRepository paymentRepository;

    @InjectMocks
    private PaymentService paymentService;

    @Test
    void makePaymentShouldSavePaymentAndSetOrderPaidWhenTotalMatches() {
        Long customerId = 1L;
        Customer customer = customer(customerId);
        Order waitingOrder = order(11L, OrderStatus.WAITING_PAYMENT, customer);

        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));
        when(orderRepository.findAllByCustomer(customer)).thenReturn(List.of(waitingOrder));
        when(orderItemRepository.findAllByOrder(waitingOrder)).thenReturn(List.of(
                orderItem(waitingOrder, 10.0, 2),
                orderItem(waitingOrder, 5.0, 1)
        ));

        PaymentDto paymentDto = new PaymentDto();
        paymentDto.setTotalValue(25.0);
        paymentDto.setPaymentMethod(PaymentMethod.PIX);

        paymentService.makePayment(customerId, paymentDto);

        assertEquals(OrderStatus.PAID, waitingOrder.getOrderStatus());
        verify(orderRepository).save(waitingOrder);

        ArgumentCaptor<Payment> paymentCaptor = ArgumentCaptor.forClass(Payment.class);
        verify(paymentRepository).save(paymentCaptor.capture());
        Payment saved = paymentCaptor.getValue();
        assertEquals(25.0, saved.getAmountPaid());
        assertEquals(PaymentMethod.PIX, saved.getPaymentMethod());
        assertEquals(waitingOrder, saved.getOrder());
    }

    @Test
    void makePaymentShouldThrowWhenTotalDoesNotMatch() {
        Long customerId = 1L;
        Customer customer = customer(customerId);
        Order waitingOrder = order(11L, OrderStatus.WAITING_PAYMENT, customer);

        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));
        when(orderRepository.findAllByCustomer(customer)).thenReturn(List.of(waitingOrder));
        when(orderItemRepository.findAllByOrder(waitingOrder)).thenReturn(List.of(
                orderItem(waitingOrder, 10.0, 2)
        ));

        PaymentDto paymentDto = new PaymentDto();
        paymentDto.setTotalValue(15.0);
        paymentDto.setPaymentMethod(PaymentMethod.CREDIT);

        assertThrows(RuntimeException.class, () -> paymentService.makePayment(customerId, paymentDto));
        verify(orderRepository, never()).save(any(Order.class));
        verify(paymentRepository, never()).save(any(Payment.class));
    }

    @Test
    void makePaymentShouldThrowWhenCustomerNotFound() {
        when(customerRepository.findById(1L)).thenReturn(Optional.empty());

        PaymentDto paymentDto = new PaymentDto();
        paymentDto.setTotalValue(10.0);
        paymentDto.setPaymentMethod(PaymentMethod.DEBIT);

        assertThrows(EntityNotFoundException.class, () -> paymentService.makePayment(1L, paymentDto));
    }

    @Test
    void makePaymentShouldThrowWhenNoWaitingPaymentOrderExists() {
        Long customerId = 1L;
        Customer customer = customer(customerId);
        Order paidOrder = order(22L, OrderStatus.PAID, customer);

        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));
        when(orderRepository.findAllByCustomer(customer)).thenReturn(List.of(paidOrder));

        PaymentDto paymentDto = new PaymentDto();
        paymentDto.setTotalValue(10.0);
        paymentDto.setPaymentMethod(PaymentMethod.DEBIT);

        assertThrows(RuntimeException.class, () -> paymentService.makePayment(customerId, paymentDto));
    }

    private Customer customer(Long id) {
        Customer customer = new Customer();
        customer.setId(id);
        return customer;
    }

    private Order order(Long id, OrderStatus status, Customer customer) {
        Order order = new Order();
        order.setId(id);
        order.setOrderStatus(status);
        order.setCustomerId(customer);
        return order;
    }

    private OrderItem orderItem(Order order, Double price, Integer quantity) {
        OrderItem item = new OrderItem();
        item.setOrderId(order);
        item.setPrice(price);
        item.setQuantity(quantity);
        return item;
    }
}
