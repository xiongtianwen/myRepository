package com.example.demo.proxy.statics;

/**
 * 代理类
 */
public class Father {

    //代理类必须持有被代理类的实例
    public Son son;

    public Father(Son son){
        this.son = son;
    }

    public void findLove(){
        System.out.println("我是父亲，替儿子找对象");
        son.findLove();
        System.out.println("after proxy");
    }

}
