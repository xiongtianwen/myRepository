package com.xtw.spring.v3.aop;

public enum AopProxyStrategy {
    //todo 这里的proxyName直接用AopJdkProxy.class、new AopJdkProxy()或者AopJdkProxy.class.getName()都不行
    // ,servlet启动或者访问到了AopProxyStrategy.JDK_PROXY这一句就卡住了
    // 但是在main方法中是可以的,目前还找不到原因
    JDK_PROXY("AopJdkProxy"),CGLIB_PROXY("AopCglibProxy");

    private String proxyName;

    AopProxyStrategy(String proxyName){
        this.proxyName = proxyName;
    }

    public String getProxyName(){
        return this.proxyName;
    }

    public static void main(String[] args) {
        String name = AopProxyStrategy.JDK_PROXY.getProxyName();
        System.out.println(name);
    }
}
