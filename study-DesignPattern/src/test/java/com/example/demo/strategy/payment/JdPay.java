package com.example.demo.strategy.payment;

public class JdPay implements Payment{
    @Override
    public PayStatus postPay(Order order) {
        System.out.println("欢迎使用京东白条支付");
        return new PayStatus(order,"支付成功！");
    }
}
