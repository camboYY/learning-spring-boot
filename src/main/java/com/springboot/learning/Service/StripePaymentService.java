package com.springboot.learning.Service;

public class StripePaymentService implements PaymentService {
    @Override
    public void processPayment(double amount) {
        System.out.println("Amount: {} "+ amount);
    }
}
