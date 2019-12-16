package com.example.demo.strategy.payment;

public interface Payment {

    public PayStatus postPay(Order order);

}
