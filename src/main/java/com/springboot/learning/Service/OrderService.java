package com.springboot.learning.Service;

import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private final PaymentService paymentService;

    public OrderService(PaymentService paymentService){
        this.paymentService = paymentService;
    }

    public void placeOrder() {
        this.paymentService.processPayment(10);
    }

}
