package com.example.demo.proxy.dynamics;

import com.example.demo.proxy.Person;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicsProxy{

    public Person person;

    public Object getInstance(Person person){
        this.person = person;
        Class clazz = person.getClass();
        return Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("我是前程无忧，我来帮你找工作");
                return method.invoke(person,args);
            }
        });
    }
}
