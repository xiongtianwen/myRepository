package com.example.demo.proxy.custom;

import com.example.demo.proxy.Person;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class CustomProxyAgent {

    public Object getNewInstance(Person target){
        return CustomProxy.newProxyInstance(new CustomClassLoader(),target.getClass().getInterfaces(), new CustomInvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) {
                System.out.println("进入自定义代理");
                try {
                     method.invoke(target,args);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
                return null;
            }
        });
    }
}
