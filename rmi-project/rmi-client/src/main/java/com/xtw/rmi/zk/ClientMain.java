package com.xtw.rmi.zk;

import com.xtw.rmi.zk.config.ZkConfig;

/**
 * @Authoor: xtw
 * @Description:
 * @Date Created in 2020/2/24 22:06
 */
public class ClientMain {
    public static void main(String[] args) throws InterruptedException {
        //链接zk
        ServiceDiscovery discovery = new ServiceDiscoveryImpl(ZkConfig.CONNECTION_STR);
        RpcClientProxy clientProxy = new RpcClientProxy(discovery);
        for(int i=0;i<5;i++) {
            HelloService hello = clientProxy.getProxy(HelloService.class, "1.0");
            System.out.println(hello.sayHello("xtw"));
            Thread.sleep(1000);
        }
    }
}
