package com.example.demo.strategy.payment;

import java.util.UUID;

public class PayStatus {
    private String code;
    private String msg;
    private Order order;

    private final String ln = "\n";

    public PayStatus(Order order,String msg){
        this.code = UUID.randomUUID().toString().replaceAll("-","");
        this.msg = msg;
        this.order = order;
    }

    public String getPayMsg(){
        StringBuffer sb = new StringBuffer();
        sb.append("支付编码：【"+code+"】").append(ln);
        sb.append("订单编码：【"+order.getOrderId()+"】").append(ln);
        sb.append("支付金额：【"+order.getPayAmount()+"】").append(ln);
        sb.append("用户id：【"+order.getUid()+"】").append(ln);
        sb.append("支付消息：【"+msg+"】").append(ln);
        return sb.toString();
    }
}
