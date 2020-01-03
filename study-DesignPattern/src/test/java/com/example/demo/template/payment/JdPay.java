package com.example.demo.template.payment;

public class JdPay extends ChinaAbstractPayment{
    @Override
    public PayStatus prePay(Order order) {
        System.out.println("欢迎使用京东支付！");
        return new PayStatus(order,"支付前操作");
    }

    @Override
    public PayStatus postPay(Order order) {
        return new PayStatus(order,"正在支付");
    }

    @Override
    public PayStatus afterPay(Order order) {
        return new PayStatus(order,"支付成功");
    }
}
