package com.xtw.spring.v2.beans;

public class BeanDefinition {



    private boolean isLazyInit = false;
    private String beanClassName;
    private String factoryBeanName;

    public void setLazyInit(boolean lazyInit) {
        isLazyInit = lazyInit;
    }

    public void setFactoryBeanName(String factoryBeanName) {
        this.factoryBeanName = factoryBeanName;
    }

    public void setBeanClassName(String beanClassName){
        this.beanClassName = beanClassName;
    }

    public String getBeanClassName(){
        return this.beanClassName;
    }

    public String getFactoryBeanName(){
        return this.factoryBeanName;
    }

    public boolean isLazyInit(){
        return isLazyInit;
    }

}
