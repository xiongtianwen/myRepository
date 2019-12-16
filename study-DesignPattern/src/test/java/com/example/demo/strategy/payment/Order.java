package com.example.demo.strategy.payment;

public class Order {
    private String orderId;
    private double payAmount;
    private String uid;

    public String getOrderId() {
        return orderId;
    }

    public double getPayAmount() {
        return payAmount;
    }

    public String getUid() {
        return uid;
    }

    public Order(String orderId, double payAmount, String uid){
        this.orderId = orderId;
        this.payAmount = payAmount;
        this.uid = uid;
    }

    public PayStatus pay(PayMode payMode){
        return payMode.getPayMent().postPay(this);
    }

}
