package com.xtw.spring.v3.aop;

public interface AopProxy {
    Object getProxy(Object instance);

    void setConfig(AopProxyConfig aopConfig);
}
