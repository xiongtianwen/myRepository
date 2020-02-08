package com.xtw.spring.v3.aop;

import java.lang.reflect.Method;

public interface AopProxy {
    Object getProxy(Object instance);

    void setConfig(AopProxyConfig aopConfig);

    Object proceed(Object proxy, Method method, Object[] args)throws Throwable;
}
