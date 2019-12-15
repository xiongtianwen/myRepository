package com.example.demo.proxy.statics;

import com.example.demo.proxy.Person;

/**
 * 被代理类
 */
public class Son implements Person {

    private String name;

    public Son(String name){
        this.name = name;
    }

    @Override
    public void findLove() {
        System.out.println(this.name + "的要求：1、貌美，2、身材好，3、年龄18-24");
    }

    @Override
    public void findJob() {

    }
}
