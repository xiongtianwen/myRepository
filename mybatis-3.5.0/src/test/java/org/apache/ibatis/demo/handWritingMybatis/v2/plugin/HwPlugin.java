package org.apache.ibatis.demo.handWritingMybatis.v2.plugin;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class HwPlugin implements InvocationHandler {

    private Object target;
    private HwInterceptor interceptor;

    public HwPlugin(Object target,HwInterceptor interceptor) {
        this.target = target;
        this.interceptor = interceptor;
    }

    public static Object wrap(Object target, HwInterceptor interceptor) {
        Class clazz = target.getClass();
        return Proxy.newProxyInstance(clazz.getClassLoader(),
                clazz.getInterfaces(),
                new HwPlugin(target,interceptor));
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return interceptor.intercept(new HwInvocation(target,method,args));
    }
}
