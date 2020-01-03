package com.example.demo.template.payment.old;

import com.example.demo.template.payment.AliPay;
import com.example.demo.template.payment.Order;
import com.example.demo.template.payment.PayStatus;
import com.example.demo.template.payment.Payment;

public class PayTest {

    public static void main(String[] args) {
        //1、用户下订单
        String userId = "20200101001";
        Order order = new Order(231.60,userId);
        //2、支付，返回支付信息
        Payment pay = new AliPay();
        PayStatus result = pay.payment(order);
        System.out.println(result.getPayMsg());
    }
}
