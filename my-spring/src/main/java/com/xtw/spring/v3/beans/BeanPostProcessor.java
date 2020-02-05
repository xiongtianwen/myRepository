package com.xtw.spring.v3.beans;

import com.xtw.spring.v3.core.FactoryBean;

/**
 * 用于事件监听的
 */
public class BeanPostProcessor extends FactoryBean {

    public Object postProcessBeforeInitialization(Object bean, String beanName){
        return bean;
    }

    public Object postProcessAfterInitialization(Object bean, String beanName){
        return bean;
    }

}
