package com.xtw.spring.v2.beans;

import com.xtw.spring.v2.core.FactoryBean;

public class BeanWrapper extends FactoryBean {

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
        this.wrapperInstance = instance;
        this.originalInstance = instance;
    }

    //Spring-framework返回的是代理后的class($proxy0)
    public Class<?> getWrappedClass(){
        return this.wrapperInstance.getClass();
    }
}
