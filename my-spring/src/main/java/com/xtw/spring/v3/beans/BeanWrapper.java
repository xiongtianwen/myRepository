package com.xtw.spring.v3.beans;

import com.xtw.spring.v3.aop.AopProxy;
import com.xtw.spring.v3.aop.AopProxyConfig;
import com.xtw.spring.v3.aop.AopProxyFactory;
import com.xtw.spring.v3.aop.AopProxyStrategy;
import com.xtw.spring.v3.core.FactoryBean;

public class BeanWrapper extends FactoryBean {
    private AopProxy aopProxy = AopProxyFactory.getProxyInstance(AopProxyStrategy.JDK_PROXY);
    private Object wrapperInstance;
    private Object originalInstance;
    private BeanPostProcessor beanPostProcessor;

    public Object getWrapperInstance() {
        return wrapperInstance;
    }

    public void setWrapperInstance(Object wrapperInstance) {
        this.wrapperInstance = wrapperInstance;
    }

    public Object getOriginalInstance() {
        return originalInstance;
    }

    public void setOriginalInstance(Object originalInstance) {
        this.originalInstance = originalInstance;
    }

    public BeanPostProcessor getBeanPostProcessor() {
        return beanPostProcessor;
    }

    public void setBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
        this.beanPostProcessor = beanPostProcessor;
    }

    public BeanWrapper(Object instance){
        //返回动态代理的实例给wrapper
        this.wrapperInstance = aopProxy.getProxy(instance);
//        this.wrapperInstance = instance;
        this.originalInstance = instance;
    }

    public void setAopConfig(AopProxyConfig aopConfig){
        this.aopProxy.setConfig(aopConfig);
    }

    //Spring-framework返回的是代理后的class($proxy0)
    public Class<?> getWrappedClass(){
        return this.wrapperInstance.getClass();
    }
}
