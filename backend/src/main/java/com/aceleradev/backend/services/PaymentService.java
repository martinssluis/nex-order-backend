package com.aceleradev.backend.services;

import com.aceleradev.backend.commons.dto.PaymentDto;
import com.aceleradev.backend.commons.enums.OrderStatus;
import com.aceleradev.backend.entities.Customer;
import com.aceleradev.backend.entities.Order;
import com.aceleradev.backend.entities.OrderItem;
import com.aceleradev.backend.entities.Payment;
import com.aceleradev.backend.repositories.CustomerRepository;
import com.aceleradev.backend.repositories.OrderItemRepository;
import com.aceleradev.backend.repositories.OrderRepository;
import com.aceleradev.backend.repositories.PaymentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PaymentService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    //TODO -- NÃO RECEBER O VALOR TOTAL DO PAGAMENTO PELO DTO -- PaymentDto
    public void makePayment(Long customerId, PaymentDto payment) {
        if (payment.getTotalValue().equals(getTotalValue(customerId))) {
            Payment paymentToSave = new Payment();

            Order order = getOrder(customerId);
            paymentToSave.setAmountPaid(payment.getTotalValue());
            paymentToSave.setPaymentMethod(payment.getPaymentMethod());
            paymentToSave.setOrderId(order);

            order.setOrderStatus(OrderStatus.PAID);

            orderRepository.save(order);
            paymentRepository.save(paymentToSave);
        } else {
            new RuntimeException("Falha ao realizar pagamento");
        }
    }

    private Customer getCustomer(Long customerId) {
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new EntityNotFoundException("Not Found"));
    }

    //TODO - BUSCAR O PAGAMENTO PELO ID DO PEDIDO E NÃO PELO CUSTOMER
    private Order getOrder(Long customerID) {
        List<Order> orderList = orderRepository.findAllByCustomer(getCustomer(customerID));
        return orderList.stream()
                .filter(it -> it.getOrderStatus() == OrderStatus.WAITING_PAYMENT)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Nenhum pedido em WAITING_PAYMENT"));
    }

    private Double getTotalValue(Long customerID) {
        List<OrderItem> item = orderItemRepository.findAllByOrder(getOrder(customerID));
        Double totalValue = 0.0;
        for (int i = 0; i < item.size(); i++) {
            int quantity  = item.get(i).getQuantity();
            totalValue += item.get(i).getPrice()*quantity;
        }
        return totalValue;
    }
}
