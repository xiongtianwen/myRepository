package com.example.demo.proxy.custom;

import com.example.demo.proxy.Person;

public class CustomProxyTest {
    public static void main(String[] args) {
        CustomProxyAgent customProxyAgent = new CustomProxyAgent();
        Object obj = customProxyAgent.getNewInstance(new Zhangsan());
        System.out.println(obj.getClass());
        Person person = (Person)obj;
        person.findLove();
    }
}
