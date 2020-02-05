package com.xtw.spring.v3.aop;

import java.lang.reflect.Field;
import java.lang.reflect.Proxy;

public class AopProxyUtil {
    public static Object getProxySourceInstance(Object proxy) throws Exception{
        if(!isJdkProxy(proxy)){return proxy;}
        return getProxyTargetObject(proxy);
    }
    public static Class<?> getProxySourceClass(Object proxy) throws Exception{
        if(!isJdkProxy(proxy)){return proxy.getClass();}
        return getProxyTargetClass(proxy);
    }

    public static boolean isJdkProxy(Object object){
        return Proxy.isProxyClass(object.getClass());
    }

    private static Object getProxyTargetObject(Object proxy) throws Exception{
        //jdk的Proxy中,InvocationHandler在代理类的父类中是字段InvocationHandler h;
        //这个字段也就是实现了InvocationHandler接口的自定义代理类
        Field h = proxy.getClass().getSuperclass().getDeclaredField("h");
        h.setAccessible(true);
        //获取自定义的代理类
        AopProxy aopJdkProxy = (AopJdkProxy)h.get(proxy);
        //获取代理中原始(被代理)的对象
        //这里取了个巧，因为自定义的AopProxy中原始的对象是字段private Object target;
        Field target = aopJdkProxy.getClass().getDeclaredField("target");
        target.setAccessible(true);
        //返回原始对象
        return target.get(aopJdkProxy);
    }

    private static Class<?> getProxyTargetClass(Object proxy) throws Exception{
        return getProxyTargetObject(proxy).getClass();
    }
}