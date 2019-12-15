package com.example.demo.proxy.custom;

import com.example.demo.proxy.Person;

public class Zhangsan implements Person {
    private String name = "张三";
    @Override
    public void findLove() {
        System.out.println(this.name + "的要求：1、貌美，2、身材好，3、年龄18-24");
    }

    @Override
    public void findJob() {
        System.out.println(this.name + "的要求：1、月薪20-50k，2、朝九晚五");
    }

}
