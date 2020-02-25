package com.xtw.rmi.zk;

import com.xtw.rmi.zk.registry.RegisterCenter;
import com.xtw.rmi.zk.registry.RegisterCenterImpl;

import java.io.IOException;

/**
 * @Authoor: xtw
 * @Description:
 * @Date Created in 2020/2/24 21:14
 */
public class ServerMain {

    public static void main(String[] args) throws IOException {
        HelloService helloService = new HelloServiceImpl();
        HelloService helloService1 = new HelloServiceImpl1();
        RegisterCenter registerCenter = new RegisterCenterImpl();
        String serviceAddr = "127.0.0.1:8080";
        RpcServer rpcServer = new RpcServer(registerCenter,serviceAddr);
        rpcServer.bind(helloService,helloService1);
        rpcServer.publishService();
    }
}
