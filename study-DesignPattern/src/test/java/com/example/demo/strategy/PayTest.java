package com.example.demo.strategy;

import com.example.demo.strategy.payment.*;

public class PayTest {
    public static void main(String[] args) {
        Order order = new Order("201912161750001",456.50,"xtw");
        System.out.println(order.pay(PayMode.JD_PAY).getPayMsg());
    }
}
