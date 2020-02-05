package com.xtw.spring.v3.aop;

public class LogAspect {

    public void before(){
        System.out.println("------------------invoke before method--------------------");
    }

    public void after(){
        System.out.println("------------------invoke after method--------------------");
    }
}
