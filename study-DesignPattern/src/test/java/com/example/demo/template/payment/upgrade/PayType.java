package com.example.demo.template.payment.upgrade;

import com.example.demo.template.payment.AliPay;
import com.example.demo.template.payment.JdPay;
import com.example.demo.template.payment.Payment;
import com.example.demo.template.payment.WechatPay;

public enum PayType {
    ALI_PAY(new AliPay()),
    WECHAT_PAY(new WechatPay()),
    JD_PAY(new JdPay());

    private Payment payment;

    PayType(Payment payment){
        this.payment = payment;
    }

    public Payment getPayment(){
        return this.payment;
    }
}
