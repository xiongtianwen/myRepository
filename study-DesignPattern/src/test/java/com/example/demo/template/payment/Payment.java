package com.example.demo.template.payment;

public interface Payment {
    PayStatus payment(Order order);
}
