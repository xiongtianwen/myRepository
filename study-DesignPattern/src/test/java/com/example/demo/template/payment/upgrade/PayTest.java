package com.example.demo.template.payment.upgrade;


import com.example.demo.template.payment.Order;
import com.example.demo.template.payment.PayStatus;

public class PayTest {
    public static void main(String[] args) {
        //下订单
        String userId = "20200101002";
        Order order = new Order(998.90,userId);
        //支付
        PayStatus result = order.pay(PayType.ALI_PAY);
        System.out.println(result.getPayMsg());
    }
}
