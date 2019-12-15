package com.example.demo.proxy.cglib;


public class CglibProxyTest {
    public static void main(String[] args){
        CglibProxy cglibProxy = new CglibProxy();
//        Object obj = cglibProxy.getInstance(Zhangsan.class);
//        System.out.println(obj.getClass());
        Zhangsan obj = (Zhangsan)cglibProxy.getInstance(Zhangsan.class);
        obj.findJob();
    }
}
