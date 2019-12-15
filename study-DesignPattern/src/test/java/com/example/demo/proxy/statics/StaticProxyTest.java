package com.example.demo.proxy.statics;

public class StaticProxyTest {

    public static void main(String[] args) {
        Father father = new Father(new Son("张三"));
        father.findLove();
    }
}
