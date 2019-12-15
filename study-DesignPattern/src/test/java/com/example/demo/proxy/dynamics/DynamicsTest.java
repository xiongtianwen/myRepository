package com.example.demo.proxy.dynamics;

import com.example.demo.proxy.Person;

public class DynamicsTest {
    public static void main(String[] args) {
        DynamicsProxy dynamicsProxy = new DynamicsProxy();
        Person person = (Person) dynamicsProxy.getInstance(new Lisi("李四"));
        person.findJob();
    }

}
