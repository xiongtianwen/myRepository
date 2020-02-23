package com.xtw.rmi.rpc;

/**
 * @Authoor: xtw
 * @Description:
 * @Date Created in 2020/2/23 20:54
 */
public class ClientMain {

    public static void main(String[] args) {
        RpcClientProxy clientProxy = new RpcClientProxy();
        HelloService helloService = clientProxy.getProxy(HelloService.class,"127.0.0.1",8888);
        String msg = helloService.sayHello("xtw");
        System.out.println(msg);
    }
}
