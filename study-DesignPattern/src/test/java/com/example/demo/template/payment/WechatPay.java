package com.example.demo.template.payment;

public class WechatPay extends ChinaAbstractPayment{
    @Override
    public PayStatus prePay(Order order) {
        System.out.println("欢迎使用微信支付！");
        return null;
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
