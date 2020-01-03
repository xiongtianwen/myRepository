package com.example.demo.template.payment;


public abstract class ChinaAbstractPayment implements Payment {
    @Override
    public PayStatus payment(Order order) {
        System.out.println(prePay(order).getPayMsg());
        System.out.println(postPay(order).getPayMsg());
        return afterPay(order);
    }
    //支付前
    public abstract PayStatus prePay(Order order);
    //支付时
    public abstract PayStatus postPay(Order order);
    //支付后
    public abstract PayStatus afterPay(Order order);

}
