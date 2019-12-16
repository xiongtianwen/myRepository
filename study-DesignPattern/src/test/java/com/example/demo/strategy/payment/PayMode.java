package com.example.demo.strategy.payment;

public enum PayMode {
    ALI_PAY(new AliPay()),
    WECHAT_PAY(new WechatPay()),
    JD_PAY(new JdPay());

    private Payment payment;

    PayMode(Payment payment){
        this.payment = payment;
    }

    public Payment getPayMent(){
        return this.payment;
    }

}
