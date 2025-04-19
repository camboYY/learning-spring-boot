package com.springboot.learning.Service;

import org.springframework.stereotype.Service;

@Service
public class PayPalService implements PaymentService{
    @Override
    public void processPayment(double amount) {
        System.out.println("Amount: "+amount);
    }
}
