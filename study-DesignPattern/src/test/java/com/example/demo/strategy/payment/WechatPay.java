package com.example.demo.strategy.payment;

public class WechatPay implements Payment{
    @Override
    public PayStatus postPay(Order order) {
        System.out.println("欢迎使用微信支付！");
        return new PayStatus(order,"支付成功!");
    }
}
