package com.example.demo.template.payment;

import com.example.demo.template.payment.upgrade.PayType;

import java.util.UUID;

public class Order {
    private String orderId;
    private double payAmount;
    private String userId;

    public Order(double payAmount, String userId){
        this.payAmount = payAmount;
        this.userId = userId;
        this.orderId = UUID.randomUUID().toString().replaceAll("-","");
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public double getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(double payAmount) {
        this.payAmount = payAmount;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public PayStatus pay(PayType payType){
        return payType.getPayment().payment(this);
    }
}
