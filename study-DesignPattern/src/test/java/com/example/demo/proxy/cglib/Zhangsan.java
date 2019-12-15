package com.example.demo.proxy.cglib;

public class Zhangsan {
    private String name = "张三";
    public void findLove() {
        System.out.println(this.name + "的要求：1、貌美，2、身材好，3、年龄18-24");
    }

    public void findJob() {
        System.out.println(this.name + "的要求：1、月薪20-50k，2、朝九晚五");
    }

}
